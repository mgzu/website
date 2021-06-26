package cc.mrbird.febs.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import cc.mrbird.febs.common.annotation.Excel;
import cc.mrbird.febs.common.entity.ImportExcelResult;
import cc.mrbird.febs.common.poi.ExcelReadListener;
import cc.mrbird.febs.others.entity.Eximport;
import cc.mrbird.febs.others.service.impl.EximportServiceImpl;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.util.CastUtils;

/**
 * @author mgzu
 * @date 2021-04-24
 */
public class ExcelUtil {

    public static void doExport(Class<?> clazz, HttpServletResponse response, List<?> data) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Excel excel = clazz.getAnnotation(Excel.class);
        Objects.requireNonNull(excel);
        String fileName = URLEncoder.encode(excel.value(), StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet("模板").doWrite(data);
    }

    public static ImportExcelResult doImport(InputStream inputStream, Class<?> clazz, ServiceImpl serviceImpl) {
        return doImport(inputStream, clazz, serviceImpl, 1);
    }

    @SuppressWarnings("unchecked")
    public static ImportExcelResult doImport(InputStream inputStream, Class<?> clazz, ServiceImpl serviceImpl, int batchInsertMaxNum) {
        ExcelReadListener listener = new ExcelReadListener(batchInsertMaxNum);
        listener.setBatchInsert(object -> serviceImpl.saveBatch(CastUtils.cast(object)));
        EasyExcel.read(inputStream, clazz, listener).doReadAll();
        return new ImportExcelResult(listener.getValidFailure(), listener.getRollback(), listener.getErrorMessages());
    }
}
