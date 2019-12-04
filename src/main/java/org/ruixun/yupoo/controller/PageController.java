package org.ruixun.yupoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* 作者：suitao*/
@Controller
@RequestMapping("/page")
public class PageController {/*页面跳转类*/

    @RequestMapping("/{pageName}")
    public String pagePath(@PathVariable("pageName") String pageName){
        return pageName;
    }
}
