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
import cc.mrbird.febs.common.utils.HttpContextUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Slf4j
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@TableName("t_login_log")
@Excel("登录日志")
public class LoginLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 921991157363932095L;
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 登录用户
     */
    @TableField("USERNAME")
    @ExcelProperty("登录用户")
    private String username;

    /**
     * 登录时间
     */
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @TableField("LOGIN_TIME")
    @ExcelProperty(value = "登录时间")
    private Date loginTime;

    /**
     * 登录地点
     */
    @TableField("LOCATION")
    @ExcelProperty(value = "登录地点")
    private String location;
    /**
     * 登录 IP
     */
    @TableField("IP")
    @ExcelProperty("登录IP")
    private String ip;
    /**
     * 操作系统
     */
    @TableField("`SYSTEM`")
    @ExcelProperty("操作系统")
    private String system;
    /**
     * 登录浏览器
     */
    @TableField("BROWSER")
    @ExcelProperty("登录浏览器")
    private String browser;

    private transient String loginTimeFrom;
    private transient String loginTimeTo;

    public void setSystemBrowserInfo() {
        try {
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

            StringBuilder userAgent = new StringBuilder("[");
            userAgent.append(request.getHeader("User-Agent"));
            userAgent.append("]");
            int indexOfMac = userAgent.indexOf("Mac OS X");
            int indexOfWindows = userAgent.indexOf("Windows NT");
            int indexOfIE = userAgent.indexOf("MSIE");
            int indexOfIE11 = userAgent.indexOf("rv:");
            int indexOfFF = userAgent.indexOf("Firefox");
            int indexOfSogou = userAgent.indexOf("MetaSr");
            int indexOfChrome = userAgent.indexOf("Chrome");
            int indexOfSafari = userAgent.indexOf("Safari");
            boolean isMac = indexOfMac > 0;
            boolean isWindows = indexOfWindows > 0;
            boolean isLinux = userAgent.indexOf("Linux") > 0;
            boolean containIE = indexOfIE > 0 || (isWindows && (indexOfIE11 > 0));
            boolean containFF = indexOfFF > 0;
            boolean containSogou = indexOfSogou > 0;
            boolean containChrome = indexOfChrome > 0;
            boolean containSafari = indexOfSafari > 0;
            String browser = "";
            if (containSogou) {
                if (containIE) {
                    browser = "搜狗" + userAgent.substring(indexOfIE, indexOfIE + "IE x.x".length());
                } else if (containChrome) {
                    browser = "搜狗" + userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
                }
            } else if (containChrome) {
                browser = userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
            } else if (containSafari) {
                int indexOfSafariVersion = userAgent.indexOf("Version");
                browser = "Safari "
                        + userAgent.substring(indexOfSafariVersion, indexOfSafariVersion + "Version/x.x.x.x".length());
            } else if (containFF) {
                browser = userAgent.substring(indexOfFF, indexOfFF + "Firefox/xx".length());
            } else if (containIE) {
                if (indexOfIE11 > 0) {
                    browser = "IE 11";
                } else {
                    browser = userAgent.substring(indexOfIE, indexOfIE + "IE x.x".length());
                }
            }
            String os = "";
            if (isMac) {
                os = userAgent.substring(indexOfMac, indexOfMac + "MacOS X xxxxxxxx".length());
            } else if (isLinux) {
                os = "Linux";
            } else if (isWindows) {
                os = "Windows ";
                String version = userAgent.substring(indexOfWindows + "Windows NT".length(), indexOfWindows
                        + "Windows NTx.x".length());
                switch (version.trim()) {
                    case "5.0":
                        os += "2000";
                        break;
                    case "5.1":
                        os += "XP";
                        break;
                    case "5.2":
                        os += "2003";
                        break;
                    case "6.0":
                        os += "Vista";
                        break;
                    case "6.1":
                        os += "7";
                        break;
                    case "6.2":
                        os += "8";
                        break;
                    case "6.3":
                        os += "8.1";
                        break;
                    case "10":
                        os += "10";
                        break;
                }
            }
            this.system = os;
            this.browser = StringUtils.replace(browser, "/", " ");
        } catch (Exception e) {
            log.error("获取登录信息失败：{}", e.getMessage());
            this.system = "";
            this.browser = "";
        }

    }
}
