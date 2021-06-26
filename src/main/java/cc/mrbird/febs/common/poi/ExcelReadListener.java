package cc.mrbird.febs.common.poi;

import cc.mrbird.febs.common.entity.BaseEntity;
import cc.mrbird.febs.common.utils.SpringContextUtil;
import cc.mrbird.febs.system.entity.User;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author mgzu
 * @date 2021-06-05
 */
@Slf4j
public class ExcelReadListener implements ReadListener<BaseEntity> {

    private int batchInsertMaxNum;
    private Validator validator;
    private List<ExcelFailureMessage> errorMessages = new ArrayList<>();
    private boolean validFailure = false;
    private boolean needRollback = false;
    private List<Object> dataList = new ArrayList<>();
    private Consumer<List<Object>> batchInsert;

    public ExcelReadListener(int batchInsertMaxNum) {
        log.debug("ExcelReadListener init");
        this.batchInsertMaxNum = batchInsertMaxNum;
        this.validator = SpringContextUtil.getBean(Validator.class);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (exception instanceof ExcelDataConvertException) {
            this.validFailure = true;
            ExcelDataConvertException e = (ExcelDataConvertException) exception;
            String errMessage = String.format("第 %s 行，第 %s 列，数据格式错误", e.getRowIndex(), e.getColumnIndex());
            Field field = e.getExcelContentProperty().getField();
            ExcelFailureMessage message = ExcelFailureMessage.builder().name(field.getName())
                    .column(String.join(StringPool.COMMA, field.getAnnotation(ExcelProperty.class).value()))
                    .errorMessage(errMessage).build();
            log.debug(errMessage);

            errorMessages.add(message);
        } else {
            log.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void invoke(BaseEntity data, AnalysisContext context) {
        log.debug("ExcelReadListener invoke");
        Set<ConstraintViolation<Object>> violations = validator.validate(data);

        if (!violations.isEmpty()) {
            this.validFailure = true;

            violations.forEach(violation -> {
                PathImpl propertyPath = (PathImpl) violation.getPropertyPath();
                String name = propertyPath.getLeafNode().getName();
                String errMessage = String.format("第 %s 行：%s", context.readRowHolder().getRowIndex(), violation.getMessage());
                log.warn(errMessage);
                Field field = FieldUtils.getField(violation.getRootBeanClass(), name, true);
                ExcelFailureMessage message = ExcelFailureMessage.builder().name(field.getName())
                        .column(String.join(StringPool.COMMA, field.getAnnotation(ExcelProperty.class).value()))
                        .errorMessage(errMessage).build();
                errorMessages.add(message);
            });
        }
        if (!this.validFailure) {
            User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
            data.setCreateTime(new Date());
            data.setCreateId(currentUser.getId());
            this.dataList.add(data);
            if (this.batchInsertMaxNum >= this.dataList.size()) {
                log.debug("ExcelReadListener invoke doBatchInsert");
                this.needRollback = true;
                this.doBatchInsert();
                this.dataList.clear();
            }
        }
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.debug("ExcelReadListener doAfterAllAnalysed");
        log.debug(this.errorMessages.stream().map(ExcelFailureMessage::getErrorMessage).collect(Collectors.joining(";")));
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        return true;
    }

    @Override
    public void invokeHead(Map headMap, AnalysisContext context) {

    }

    /**
     * 分批导入到数据库
     *
     * @param batchInsert 分批导入
     */
    public void setBatchInsert(Consumer<List<Object>> batchInsert) {
        this.batchInsert = batchInsert;
    }

    public void doBatchInsert() {
        this.batchInsert.accept(this.dataList);
    }

    public boolean getValidFailure() {
        return this.validFailure;
    }

    /**
     * 当插入数据，且存在数据校验错误，则需要回滚数据
     *
     * @return 是否需要回滚数据
     */
    public boolean getRollback() {
        return this.needRollback && this.validFailure;
    }

    public List<ExcelFailureMessage> getErrorMessages() {
        return this.errorMessages;
    }
}
