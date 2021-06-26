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

package cc.mrbird.febs.official.entity;

import java.util.Date;

import cc.mrbird.febs.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 站点管理 Entity
 *
 * @author mgzu
 * @date 2019-09-22 21:06:36
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_site")
public class Site extends BaseEntity {

    /**
     * 站点编号
     */
    @TableId(value = "SITE_ID", type = IdType.AUTO)
    private Integer siteId;

    /**
     * 站点名称
     */
    @TableField("SITE_NAME")
    private String siteName;

    /**
     * 站点简称
     */
    @TableField("SITE_PATH")
    private String sitePath;

    /**
     * 关键字
     */
    @TableField("KEYWORDS")
    private String keywords;

    /**
     * 域名
     */
    @TableField("DOMAIN_NAME")
    private String domainName;

    /**
     * 访问协议
     */
    @TableField("ACCESS_PROTOCOL")
    private String accessProtocol;

    /**
     * 访问地址
     */
    @TableField("ACCESS_PATH")
    private String accessPath;

    /**
     * 排序
     */
    @TableField("SORT")
    private String sort;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    /**
     * 0、删除 1、正常
     */
    @TableField("STATUS")
    private String status;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

}
