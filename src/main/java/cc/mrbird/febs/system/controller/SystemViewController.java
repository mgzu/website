package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.authentication.ShiroHelper;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author MrBird
 */
@Slf4j
@Controller
public class SystemViewController extends BaseController implements ErrorController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    public Object login() {
        return "redirect:/" + FebsConstant.ADMIN_MAPPING_PREFIX + "login";
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "login")
    @ResponseBody
    public Object adminLogin(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("admin_mapping_prefix", FebsConstant.ADMIN_MAPPING_PREFIX);
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return FebsUtil.view("error/403");
    }

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        user.setPassword("It's a secret");
        model.addAttribute("user", userService.findByName(user.getUsername())); // 获取实时的用户信息
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        model.addAttribute("admin_mapping_prefix", FebsConstant.ADMIN_MAPPING_PREFIX);
        return FebsUtil.view("index");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "layout")
    public String layout(Model model) {
        model.addAttribute("admin_mapping_prefix", FebsConstant.ADMIN_MAPPING_PREFIX);
        return FebsUtil.view("layout");
    }


    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "home")
    public String home() {
        return FebsUtil.view("home");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    /**
     * 管理端 404
     */
    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "404")
    public String adminError404() {
        return FebsUtil.view("error/404");
    }

    /**
     * 管理端 403
     */
    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "403")
    public String adminError403() {
        return FebsUtil.view("error/403");
    }

    /**
     * 管理端 500
     */
    @GetMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "500")
    public String adminError500() {
        return FebsUtil.view("error/500");
    }

    /**
     * web 端 index 页面
     */
    @GetMapping("index")
    public String pageIndex() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /**
     * web 端错误页面
     */
    @RequestMapping("error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return FebsUtil.webView("error/404");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return FebsUtil.webView("error/403");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return FebsUtil.webView("error/500");
            }
            log.error("系统访问异常，状态码：{}", statusCode);
        }
        return FebsUtil.webView("error/error");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
            else user.setSex("保密");
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
}
