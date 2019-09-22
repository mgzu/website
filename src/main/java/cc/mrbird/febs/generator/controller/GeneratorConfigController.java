package cc.mrbird.febs.generator.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.generator.entity.GeneratorConfig;
import cc.mrbird.febs.generator.service.IGeneratorConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author MrBird
 */
@Slf4j
@RestController
@RequestMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "generatorConfig")
public class GeneratorConfigController extends BaseController {

    @Autowired
    private IGeneratorConfigService generatorConfigService;

    @GetMapping
    @RequiresPermissions("generator:configure:view")
    public FebsResponse getGeneratorConfig() {
        return new FebsResponse().success().data(generatorConfigService.findGeneratorConfig());
    }

    @ControllerEndpoint(value = "修改 GeneratorConfig", exceptionMessage = "修改 GeneratorConfig 失败")
    @PostMapping("update")
    @RequiresPermissions("generator:configure:update")
    public FebsResponse updateGeneratorConfig(@Valid GeneratorConfig generatorConfig) throws FebsException {
        if (StringUtils.isBlank(generatorConfig.getId()))
            throw new FebsException("配置id不能为空");
        this.generatorConfigService.updateGeneratorConfig(generatorConfig);
        return new FebsResponse().success();
    }
}
