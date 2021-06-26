package cc.mrbird.febs.others.service.impl;

import cc.mrbird.febs.common.entity.ImportExcelResult;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.ExcelUtil;
import cc.mrbird.febs.others.entity.Eximport;
import cc.mrbird.febs.others.mapper.EximportMapper;
import cc.mrbird.febs.others.service.IEximportService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.InputStream;

/**
 * @author MrBird
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EximportServiceImpl extends ServiceImpl<EximportMapper, Eximport> implements IEximportService {

    @Value("${febs.max.batch.insert.num:1000}")
    private int batchInsertMaxNum;

    @Override
    public IPage<Eximport> findEximports(QueryRequest request, Eximport eximport) {
        Page<Eximport> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, null);
    }

    @Transactional
    @Override
    public ImportExcelResult importExcel(InputStream inputStream) {
        ImportExcelResult importExcelResult = ExcelUtil.doImport(inputStream, Eximport.class, this);

        if (importExcelResult.isRollback()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.debug("rollback import data");
        }
        return importExcelResult;
    }

}
