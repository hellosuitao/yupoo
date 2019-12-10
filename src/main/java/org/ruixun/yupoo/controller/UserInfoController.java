package org.ruixun.yupoo.controller;

import org.aspectj.lang.annotation.Aspect;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.LoginUserService;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author suitao
 * @description
 */
@Controller("/userInfo")
public class UserInfoController {
    @Autowired
    private LoginUserService loginUserService;
    @RequestMapping("/info")
    @ResponseBody
    public Result findInfo(HttpServletRequest request){
        Users user = FindUser.findUser(request);
        System.out.println(user);
        return ResultUtils.buildSuccess(user);
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public Result updateInfo(@RequestBody Users users, HttpServletRequest request){
        loginUserService.updateInfo(users);
        return ResultUtils.buildSuccess();
    }

    @RequestMapping("/findAllCustomer")
    @ResponseBody
    /*查询用户信息库*/
    public Result findAllCustomer(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  HttpServletRequest request,
                                  ModelMap map){
        Users user = FindUser.findUser(request);
        PageRequest pageRequest = new PageRequest(page,size);
        Page<Users> usersPage = loginUserService.findAllCustomer(pageRequest,user);
        if(usersPage.getContent()!=null&&usersPage.getContent().size()>0){
            return ResultUtils.buildSuccess(usersPage);
        }return ResultUtils.buildFail("No Customers");
    }

}
