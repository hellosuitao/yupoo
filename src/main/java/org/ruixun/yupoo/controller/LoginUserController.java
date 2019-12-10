package org.ruixun.yupoo.controller;

import org.apache.catalina.User;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.LoginUserService;
import org.ruixun.yupoo.utils.CustomException;
import org.ruixun.yupoo.utils.FindUser;
import org.ruixun.yupoo.utils.Result;
import org.ruixun.yupoo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/*
    controller
    登录模块
 */
@RestController
@RequestMapping("/login")
public class LoginUserController {

    @Autowired
    private LoginUserService loginUserService;


    Logger logger = LoggerFactory.getLogger(LoginUserController.class);

    /*
        登录
     */
    @ResponseBody
    @RequestMapping(value = "/log",method = RequestMethod.POST)
    public Result login(String email, String password
            , HttpServletRequest request, HttpServletResponse response, ModelMap map){
        System.out.println("成功访问");
        Users user = null;
        try {
            user = loginUserService.login(email, password);
            System.out.println("成功");
        } catch (CustomException e) {
            e.printStackTrace();
            return ResultUtils.buildFail(e.getMessage());
        }
        String token = UUID.randomUUID().toString().replace("-", "");

        logger.info("token : {}",token);

        //token 是一个uuid 里面存储的是 用户的信息
        request.getSession().setAttribute(token,user);
        //存储一个 key为token的 值为session的key的cookie
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        cookie.setMaxAge(60*60*24);
        String name = cookie.getName();
        String path = cookie.getPath();
        //获取权限
        List<String> permissions = null;
        try {
            permissions = loginUserService.getpermissions(email);
        } catch (CustomException e) {
            e.printStackTrace();
            return ResultUtils.buildFail("ERROR! Permission denied");
        }
        if(permissions==null){
            return ResultUtils.buildFail("Unopened permission");
        }
        //把权限存储到session  key 为"permission"+token
        request.getSession().setAttribute("permission"+token,permissions);
        System.out.println(permissions);
        //邮箱名 加 token 里面存储的是 list<权限路径>
        request.getSession().setAttribute(email+token,permissions);
       user.setPassword("");
       user.setSalt("");
       user.setEmail("");
        return ResultUtils.buildSuccess(user);
    }
    /*
        退出登录的方法
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public Result logout(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies !=null){
            for (Cookie cookie : cookies) {
                String cookieKey = cookie.getName();
                System.out.println("cookie   "+cookie.getName());
                if("token".equals(cookieKey)){
                    String value = cookie.getValue();
                    if (value != null) {
                        request.getSession().removeAttribute(value);
                        String path = cookie.getPath();
                        System.out.println(path);
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        System.out.println("执行了");
                        return ResultUtils.buildSuccess();
                    }
                }
            }
        }
        return ResultUtils.buildFail(" server busy");
    }

    @RequestMapping("/index")
    @ResponseBody
    public Result longindex(){
        System.out.println("aaa");

        return ResultUtils.buildFail("access fail");

    }
}
