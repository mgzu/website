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

import cc.mrbird.febs.common.annotation.Dict;
import cc.mrbird.febs.common.annotation.Excel;
import cc.mrbird.febs.common.converter.DictConverter;
import cc.mrbird.febs.common.entity.BaseEntity;
import com.alibaba.excel.annotation.ExcelIgnore;
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
@TableName("t_menu")
@Excel("菜单信息表")
public class Menu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8571011372410167901L;

    // 菜单
    public static final String TYPE_MENU = "0";
    // 按钮
    public static final String TYPE_BUTTON = "1";

    public static final Long TOP_NODE = 0L;

    /**
     * 菜单/按钮ID
     */
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @ExcelIgnore
    @TableField("PARENT_ID")
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @TableField("MENU_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelProperty(value = "名称")
    private String menuName;

    /**
     * 菜单URL
     */
    @TableField("URL")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelProperty(value = "URL")
    private String url;

    /**
     * 权限标识
     */
    @TableField("PERMS")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelProperty(value = "权限")
    private String perms;

    /**
     * 图标
     */
    @TableField("ICON")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelProperty(value = "图标")
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @Dict("MENU_TYPE")
    @TableField("TYPE")
    @NotBlank(message = "{required}")
    @ExcelProperty(value = "类型", converter = DictConverter.class)
    private String type;

    /**
     * 排序
     */
    @TableField("ORDER_NUM")
    private Long orderNum;

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


}
