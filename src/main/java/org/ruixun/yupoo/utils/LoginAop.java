package org.ruixun.yupoo.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//@Aspect
//@Component
//@Configuration
/*
    aop 对controller里面的方法进行操作
    作者：李万君  暂时没啥用
 */
public class LoginAop {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution( * org.ruixun.yupoo.controller.LoginUserController.*(..) )")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(){
        if ("/login/log".equals(request.getRequestURI())){return;}
        System.out.println("切面运行了");
        Cookie[] cookies = request.getCookies();
        if (cookies.length !=0 && cookies !=null){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())){
                    System.out.println("--------");
                    System.out.println(cookie.getPath());
                    cookie.setPath("/login");
                    cookie.setMaxAge(0);
                }
            }
        }
    }
    @After("pointcut()")
    public void after(){
        if ("/login/log".equals(request.getRequestURI())){return;}
        Cookie[] cookies = request.getCookies();
        if (cookies.length !=0){
            for (Cookie cookie : cookies) {
            String name = cookie.getName();
            System.out.println("cookie   " + name);
            }
        }
    }
    @AfterReturning("pointcut()")
    public void  afterReturning(){}

    @AfterThrowing("pointcut()")
    public void afterThrowing(){}
}
