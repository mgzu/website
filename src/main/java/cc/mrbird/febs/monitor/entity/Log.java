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

package cc.mrbird.febs.monitor.entity;

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

import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_log")
@Excel("系统日志表")
public class Log extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 操作用户
     */
    @TableField("USERNAME")
    @ExcelProperty(value = "操作用户")
    private String username;

    /**
     * 操作内容
     */
    @TableField("OPERATION")
    @ExcelProperty(value = "操作内容")
    private String operation;

    /**
     * 耗时
     */
    @TableField("TIME")
    @ExcelProperty(value = "耗时（毫秒）")
    private Long time;

    /**
     * 操作方法
     */
    @TableField("METHOD")
    @ExcelProperty(value = "操作方法")
    private String method;

    /**
     * 方法参数
     */
    @TableField("PARAMS")
    @ExcelProperty(value = "方法参数")
    private String params;

    /**
     * 操作者IP
     */
    @TableField("IP")
    @ExcelProperty(value = "操作者IP")
    private String ip;

    /**
     * 创建时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @TableField("CREATE_TIME")
    @ExcelProperty(value = "操作时间")
    private Date createTime;

    /**
     * 操作地点
     */
    @TableField("LOCATION")
    @ExcelProperty(value = "操作地点")
    private String location;

    private transient String createTimeFrom;
    private transient String createTimeTo;
}
