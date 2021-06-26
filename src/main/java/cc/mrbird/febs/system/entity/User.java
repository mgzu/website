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
import cc.mrbird.febs.common.annotation.IsMobile;
import cc.mrbird.febs.common.converter.DictConverter;
import cc.mrbird.febs.common.entity.BaseEntity;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
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
@TableName("t_user")
@Excel("用户信息表")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

    // 用户状态：有效
    public static final String STATUS_VALID = "1";
    // 用户状态：锁定
    public static final String STATUS_LOCK = "0";
    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";
    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";
    // 性别保密
    public static final String SEX_UNKNOWN = "2";
    // 黑色主题
    public static final String THEME_BLACK = "black";
    // 白色主题
    public static final String THEME_WHITE = "white";
    // TAB开启
    public static final String TAB_OPEN = "1";
    // TAB关闭
    public static final String TAB_CLOSE = "0";


    /**
     * 用户 ID
     */
    @ExcelIgnore
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    @Size(min = 4, max = 10, message = "{range}")
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelIgnore
    @TableField("PASSWORD")
    private String password;

    /**
     * 部门 ID
     */
    @ExcelIgnore
    @TableField("DEPT_ID")
    private Long deptId;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 联系电话
     */
    @TableField("MOBILE")
    @IsMobile(message = "{mobile}")
    @ExcelProperty(value = "联系电话")
    private String mobile;

    /**
     * 状态 0锁定 1有效
     */
    @ExcelProperty(value = "状态")
    @TableField("STATUS")
    @NotBlank(message = "{required}")
    private String status;

    /**
     * 创建时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "最近访问时间")
    @TableField("LAST_LOGIN_TIME")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 性别 0男 1女 2 保密
     */
    @Dict(value = "SSEX")
    @ExcelProperty(value = "性别", converter = DictConverter.class)
    @TableField("SSEX")
    @NotBlank(message = "{required}")
    private String sex;

    /**
     * 头像
     */
    @ExcelIgnore
    @TableField("AVATAR")
    private String avatar;

    /**
     * 主题
     */
    @ExcelIgnore
    @TableField("THEME")
    private String theme;

    /**
     * 是否开启 tab 0开启，1关闭
     */
    @ExcelIgnore
    @TableField("IS_TAB")
    private String isTab;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    @Size(max = 100, message = "{noMoreThan}")
    @ExcelProperty(value = "个人描述")
    private String description;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门")
    @TableField(exist = false)
    private String deptName;

    @ExcelIgnore
    @TableField(exist = false)
    private String createTimeFrom;

    @ExcelIgnore
    @TableField(exist = false)
    private String createTimeTo;
    /**
     * 角色 ID
     */
    @ExcelIgnore
    @NotBlank(message = "{required}")
    @TableField(exist = false)
    private String roleId;

    @ExcelProperty(value = "角色")
    @TableField(exist = false)
    private String roleName;

    public Long getId() {
        return userId;
    }
}
