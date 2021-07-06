/*
 *    Copyright 2021 [website of copyright mrbird & mgzu]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
 * 字典详情表
 * </p>
 *
 * @author mgzu
 * @since 2021-06-26
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_dict_detail")
@ApiModel(value = "DictDetail对象", description = "字典详情表")
public class DictDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典ID")
    @TableId(value = "DICT_DETAIL_ID", type = IdType.AUTO)
    private Long dictDetailId;

    @ApiModelProperty(value = "父ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "标签")
    @TableField("LABEL")
    private String label;

    @ApiModelProperty(value = "值")
    @TableField("VALUE")
    private String value;

    @ApiModelProperty(value = "排序")
    @TableField("ORDER_NUM")
    private Integer orderNum;

    @ApiModelProperty(value = "字典状态")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "VALUE 的数据类型")
    @TableField("DATA_TYPE")
    private Integer dataType;

}
