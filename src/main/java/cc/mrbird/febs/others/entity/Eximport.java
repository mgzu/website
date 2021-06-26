package cc.mrbird.febs.others.entity;

import cc.mrbird.febs.common.annotation.Excel;
import cc.mrbird.febs.common.entity.BaseEntity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 导入导出测试，Eximport = export + import
 *
 * @author MrBird
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_eximport")
@Excel("测试导入导出数据")
public class Eximport extends BaseEntity {

    /**
     * 字段1
     */
    @Length(max = 20)
    @NotBlank
    @ExcelProperty("字段1")
    private String field1;

    /**
     * 字段2
     */
    @ExcelProperty("字段2")
    private Integer field2;

    /**
     * 字段3
     */
    @Email
    @ExcelProperty("字段3")
    private String field3;

}