package cc.mrbird.febs.system.controller;


import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.DeptTree;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.ExcelUtil;
import cc.mrbird.febs.system.entity.Dept;
import cc.mrbird.febs.system.service.IDeptService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @ControllerEndpoint(exceptionMessage = "获取部门树失败")
    @GetMapping("select/tree")
    public List<DeptTree<Dept>> getDeptTree() throws FebsException {
        try {
            return this.deptService.findDepts();
        } catch (Exception e) {
            String message = "获取部门树失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @ControllerEndpoint(exceptionMessage = "获取部门树失败")
    @GetMapping("tree")
    public FebsResponse getDeptTree(Dept dept) {
        List<DeptTree<Dept>> depts = this.deptService.findDepts(dept);
        return new FebsResponse().success().data(depts);
    }

    @ControllerEndpoint(value = "新增部门",exceptionMessage = "新增部门失败")
    @PostMapping
    @RequiresPermissions("dept:add")
    public FebsResponse addDept(@Valid Dept dept) {
        this.deptService.createDept(dept);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(value = "删除部门", exceptionMessage = "删除部门失败")
    @GetMapping("delete/{deptIds}")
    @RequiresPermissions("dept:delete")
    public FebsResponse deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) {
        String[] ids = deptIds.split(StringPool.COMMA);
        this.deptService.deleteDepts(ids);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(value = "修改部门", exceptionMessage = "修改部门失败")
    @PostMapping("update")
    @RequiresPermissions("dept:update")
    public FebsResponse updateDept(@Valid Dept dept) {
        this.deptService.updateDept(dept);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    @GetMapping("excel")
    @RequiresPermissions("dept:export")
    public void export(Dept dept, QueryRequest request, HttpServletResponse response) throws IOException {
        List<Dept> depts = this.deptService.findDepts(dept, request);
        ExcelUtil.doExport(Dept.class, response, depts);
    }
}
