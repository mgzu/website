package cc.mrbird.febs.test;

import cc.mrbird.febs.others.entity.Eximport;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mgzu
 * @date 2021-06-05
 */
@Slf4j
public class EasyExcelImportTest {

    @Test
    public void TestImport() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        File file = new File("/Users/gzm/Downloads/Excel导出结果.xlsx");

        Stopwatch stopwatch = Stopwatch.createStarted();
        final List<Eximport> data = Lists.newArrayList();
        final List<Map<String, Object>> error = Lists.newArrayList();

        EasyExcel.read(file, Eximport.class, new ReadListener() {
            @Override
            public void onException(Exception exception, AnalysisContext context) throws Exception {
                if (exception instanceof ExcelDataConvertException) {

                }
                log.error(exception.getMessage(), exception);
            }

            @Override
            public void invokeHead(Map headMap, AnalysisContext context) {

            }

            @Override
            public void invoke(Object eximport, AnalysisContext context) {
//                eximport.setCreateTime(new Date());
//                data.add(eximport);
                Set<ConstraintViolation<Object>> violations = validator.validate(eximport);
                if (!violations.isEmpty()) {
                    String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
                    log.warn(message);
                }
            }

            @Override
            public void extra(CellExtra extra, AnalysisContext context) {

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }

            @Override
            public boolean hasNext(AnalysisContext context) {
                return true;
            }
        }).sheet().doRead();

        ImmutableMap<String, Object> result = ImmutableMap.of(
                "time", stopwatch.stop().toString(),
                "data", data,
                "error", error
        );

        log.info("data size: {}", data.size());

    }
}
