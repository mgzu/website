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

package cc.mrbird.febs.common.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author mgzu
 * @since 2021-06-25
 */
@Setter
@Getter
public class BaseEntity {

    /**
     * 创建人 ID
     */
    @ExcelIgnore
    @TableField("CREATE_ID")
    private Long createId;
    /**
     * 创建时间
     */
    @ExcelIgnore
    @TableField("CREATE_TIME")
    private Date createTime;
    /**
     * 修改人 ID
     */
    @ExcelIgnore
    @TableField("MODIFY_ID")
    private Long modifyId;
    /**
     * 修改时间
     */
    @ExcelIgnore
    @TableField("MODIFY_TIME")
    private Date modifyTime;
    /**
     * 备注
     */
    @ExcelIgnore
    @TableField("REMARK")
    private String remark;
    /**
     * 乐观锁
     */
    @ExcelIgnore
    @TableField("VERSION")
    private String version;

}
