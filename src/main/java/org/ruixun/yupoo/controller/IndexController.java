package org.ruixun.yupoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    访问静态页面 可以通过/ruixun/....拿取静态页面
    作者：李万君
 */
@Controller
@RequestMapping("/ruixun")
public class IndexController {

    @RequestMapping("/{index}")
    public String index(@PathVariable(value="index") String index){
        System.out.println(index);
        return index;
    }

}
