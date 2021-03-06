package cc.mrbird.febs.system.controller;


import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.ExcelUtil;
import cc.mrbird.febs.system.entity.Role;
import cc.mrbird.febs.system.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@Slf4j
@RestController
@RequestMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public FebsResponse getAllRoles(Role role) {
        return new FebsResponse().success().data(roleService.findRoles(role));
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    public FebsResponse roleList(Role role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(value = "新增角色", exceptionMessage = "新增角色失败")
    @PostMapping
    @RequiresPermissions("role:add")
    public FebsResponse addRole(@Valid Role role) {
        this.roleService.createRole(role);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(value = "删除角色", exceptionMessage = "删除角色失败")
    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) {
        this.roleService.deleteRoles(roleIds);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(value = "修改角色", exceptionMessage = "修改角色失败")
    @PostMapping("update")
    @RequiresPermissions("role:update")
    public FebsResponse updateRole(Role role) {
        this.roleService.updateRole(role);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(exceptionMessage = "导出 Excel 失败")
    @GetMapping("excel")
    @RequiresPermissions("role:export")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws IOException {
        List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
        ExcelUtil.doExport(Role.class, response, roles);
    }

}
