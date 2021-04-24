package cc.mrbird.febs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author https://github.com/mgzu
 * @date 2019-09-15
 */
@Controller("web/ViewController")
public class ViewController {

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    /**
     * web 端 index 页面
     */
    @GetMapping("index")
    public String pageIndex() {
        return "index";
    }

}
