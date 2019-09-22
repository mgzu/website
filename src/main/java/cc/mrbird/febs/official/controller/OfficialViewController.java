package cc.mrbird.febs.official.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author https://github.com/mgzu
 */
@Controller
@RequestMapping(FebsConstant.ADMIN_MAPPING_PREFIX + "official")
public class OfficialViewController extends BaseController {

    @GetMapping("content")
    public String content() {
        return FebsUtil.view("official/content");
    }

}
