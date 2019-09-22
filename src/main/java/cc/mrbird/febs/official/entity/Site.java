package cc.mrbird.febs.official.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 站点管理 Entity
 *
 * @author mgzu
 * @date 2019-09-22 21:06:36
 */
@Data
@TableName("t_site")
public class Site {

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
