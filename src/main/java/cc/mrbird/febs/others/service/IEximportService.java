package cc.mrbird.febs.others.service;


import cc.mrbird.febs.common.entity.ImportExcelResult;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.others.entity.Eximport;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * @author MrBird
 */
public interface IEximportService extends IService<Eximport> {
    /**
     * 查询（分页）
     *
     * @param request  QueryRequest
     * @param eximport eximport
     * @return IPage<Eximport>
     */
    IPage<Eximport> findEximports(QueryRequest request, Eximport eximport);

    /**
     * 使用 Service 导入 excel，纳入事物范围
     * 当全部导入成功时才算成功，否则，出现异常时，回滚数据，返回所有错误信息
     *
     * @param inputStream
     * @return
     */
    ImportExcelResult importExcel(InputStream inputStream);

}
