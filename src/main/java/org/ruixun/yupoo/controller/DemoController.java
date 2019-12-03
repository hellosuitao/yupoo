package org.ruixun.yupoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    测试用的，可以验证是否登录
    作者李万君
 */
@Controller
@RequestMapping("/album")
public class DemoController {

    @RequestMapping("/addAlbum")
    public String teest(){

        return "succee";
    }
}
