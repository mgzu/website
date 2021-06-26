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

import cc.mrbird.febs.common.annotation.Excel;
import cc.mrbird.febs.common.entity.BaseEntity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_role")
@Excel("角色信息表")
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4493960686192269860L;
    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    @ExcelProperty(value = "角色名称")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField("REMARK")
    @ExcelProperty(value = "角色描述")
    @Size(max = 50, message = "{noMoreThan}")
    private String remark;

    /**
     * 创建时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @TableField("CREATE_TIME")
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @TableField("MODIFY_TIME")
    @ExcelProperty(value = "修改时间")
    private Date modifyTime;

    /**
     * 角色对应的菜单（按钮） id
     */
    private transient String menuIds;
}
