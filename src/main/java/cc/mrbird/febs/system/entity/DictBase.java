package cc.mrbird.febs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import cc.mrbird.febs.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author mgzu
 * @since 2021-06-26
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_dict_base")
@ApiModel(value = "DictBase对象", description = "字典表")
public class DictBase extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典ID")
    @TableId(value = "DICT_ID", type = IdType.AUTO)
    private Long dictId;

    @ApiModelProperty(value = "key")
    @TableField("KEY")
    private String key;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    @ApiModelProperty(value = "字典状态")
    @TableField("STATUS")
    private Integer status;


}
