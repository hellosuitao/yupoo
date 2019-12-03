package org.ruixun.yupoo.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.Map;

/**
 * @author suitao
 * @description
 */
@Controller
public class Hello {

    @RequestMapping("/hello/demo1")
    public String demo1(ModelMap map){
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f.getAbsoluteFile());
        map.put("image","http://127.0.0.1:8080/image/4.jpg");
        return "hellodemo";
    }
}
