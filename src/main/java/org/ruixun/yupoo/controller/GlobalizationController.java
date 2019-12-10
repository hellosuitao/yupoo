package org.ruixun.yupoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GlobalizationController {
    //    admin登录页面控制器（必须先走此控制器访问页面，不能直接进行访问）
    @RequestMapping("adminLoginGo")
    public String adminLogin(){
        System.out.println("进adminLoginGo");
        return "adminLogin";
    }

    @RequestMapping("navGo")
    public String nav(){
        System.out.println("进navGo");
        return "nav";
    }
}
