package cc.mrbird.febs.system.controller;


import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.MenuTree;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.ExcelUtil;
import cc.mrbird.febs.system.entity.Menu;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

/**
 * @author MrBird
 */
@Slf4j
@RestController
@RequestMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("{username}")
    public FebsResponse getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws FebsException {
        User currentUser = getCurrentUser();
        if (!StringUtils.equalsIgnoreCase(username, currentUser.getUsername()))
            throw new FebsException("您无权获取别人的菜单");
        MenuTree<Menu> userMenus = this.menuService.findUserMenus(username);
        return new FebsResponse().data(userMenus);
    }

    @ControllerEndpoint(exceptionMessage = "获取菜单树失败")
    @GetMapping("tree")
    public FebsResponse getMenuTree(Menu menu) {
        MenuTree<Menu> menus = this.menuService.findMenus(menu);
        return new FebsResponse().success().data(menus.getChilds());
    }

    @ControllerEndpoint(value = "新增菜单/按钮", exceptionMessage = "新增菜单/按钮失败")
    @PostMapping
    @RequiresPermissions("menu:add")
    public FebsResponse addMenu(@Valid Menu menu) {
        this.menuService.createMenu(menu);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(value = "删除菜单/按钮", exceptionMessage = "删除菜单/按钮失败")
    @GetMapping("delete/{menuIds}")
    @RequiresPermissions("menu:delete")
    public FebsResponse deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds) {
        this.menuService.deleteMeuns(menuIds);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(value = "修改菜单/按钮", exceptionMessage = "修改菜单/按钮失败")
    @PostMapping("update")
    @RequiresPermissions("menu:update")
    public FebsResponse updateMenu(@Valid Menu menu) {
        this.menuService.updateMenu(menu);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(exceptionMessage = "导出 Excel 失败")
    @GetMapping("excel")
    @RequiresPermissions("menu:export")
    public void export(Menu menu, HttpServletResponse response) throws IOException {
        List<Menu> menus = this.menuService.findMenuList(menu);
        ExcelUtil.doExport(Menu.class, response, menus);
    }
}
