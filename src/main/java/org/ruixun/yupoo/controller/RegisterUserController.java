package org.ruixun.yupoo.controller;

import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.service.RegisterUserService;
import org.ruixun.yupoo.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/*
    controller
    注册模块
    作者：李万君
 */
@RestController
@RequestMapping("/register")
public class RegisterUserController {

    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private UsernameUtils usernameUtils;
    @Autowired
    private RegisterUserService registerUserService;
    @Autowired
    private PasswordUtils passwordUtils;

    Logger logger = LoggerFactory.getLogger(RegisterUserController.class);

    /*
        判断前端 用户名是否合法，和数据库查重
     */
//    @CrossOrigin(origins = "http://localhost:8080" )
    @RequestMapping(value = "/username",method = RequestMethod.POST)
    public Result loginByUsername(String username, HttpServletRequest request){
        System.out.println("username  "+username);
        //如果是空的或者不合法，直接返回前端
        if (!usernameUtils.isUsername(username)){
            return ResultUtils.buildFail("illegal Nick name");
        }
        List<Users> oneByusername = registerUserService.findOneByusername(username);
        if (oneByusername.size()==0 || oneByusername.isEmpty()){
            //进来了表示数据库中没有数据
            return ResultUtils.buildSuccess();
        }
        //返回给前端
        return ResultUtils.buildFail("Nick name already in use");
    }


    /*
        验证前端 邮箱 是否合法，数据库查重
     */
    @RequestMapping("/email")
    public Result loginByEmail(String email,HttpServletRequest request) {

        if (request.getSession().getAttribute(email) != null || "".equals(request.getSession().getAttribute(email))){
            request.getSession().removeAttribute(email);
            return ResultUtils.buildFail("please wait 30s to continue");
        }

        System.out.println(email);
        boolean a = emailUtils.isEmail(email);
        System.out.println(a);
        //如果是空的或者不合法，直接返回前端
        if (!emailUtils.isEmail(email)) {
            return ResultUtils.buildFail("illegal Email");
        }
        System.out.println("--------aa");
        //数据库查重
        List<Users> oneByEmail = registerUserService.findOneByEmail(email);
        if (oneByEmail.size()==0 || oneByEmail.isEmpty()){
            //生成随机的6位验证码
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message = "your verification code is："+checkCode;
            logger.info("your verification is : {}",checkCode);

//            Cookie cookie = new Cookie("email"+checkCode,email+checkCode);
//            cookie.setMaxAge(300);
//            response.addCookie(cookie);
            //进来了表示数据库中没有数据
            System.out.println(email);
            System.out.println(message);
            registerUserService.sendSimpleMail(email,"睿讯验证码",message);

            String replace = UUID.randomUUID().toString().replace("-", "");
            HttpSession session2 = request.getSession();
            session2.setAttribute(email,replace);
            session2.setMaxInactiveInterval(30);
            //session存储的是邮箱加验证码
            HttpSession session = request.getSession();
            session.setAttribute(email+checkCode,email+checkCode);
            session.setMaxInactiveInterval(5*60);



            return ResultUtils.buildSuccess();
        }
        System.out.println("----");
        //返回给前端
        return ResultUtils.buildFail("Email registered already");
    }



    /*
        保存前端用户的 注册信息
     */
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Result register(String username, String password, String password2
            , String email, @RequestParam(value = "checkCode")String checkCode, HttpServletRequest request){
        System.out.println(username+"  "+password+"  "+password2+"  "+email+"  "+checkCode);
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        users.setPassword2(password2);
        users.setEmail(email);
        users.setCheckCode(checkCode);
        //判断前端传递来的信息是否符合我们的要求
        try {
            registerUserService.check(users);
        } catch (CustomException e) {
            e.printStackTrace();
            return ResultUtils.buildFail(e.getMessage());
        }
     /*   //为空直接返回
        if (email == null || "".equals(email)){
            System.out.println(3);

            return ResultUtils.buildFail("邮箱不能为空");
        }
        //如果是空的或者不合法，直接返回前端
        if (!usernameUtils.isUsername(username)){
            return ResultUtils.buildFail("用户名不合法");
        }
        List<Users> oneByusername = registerUserService.findOneByusername(username);
        if (oneByusername.size()!=0 || !oneByusername.isEmpty()){
            //返回给前端
            return ResultUtils.buildFail("用户名已被使用");
        }
        if (username==null || "".equals(username)){
            return ResultUtils.buildFail("用户名不能为空");
        }
        if (!passwordUtils.isPasswordUtils(password)){
            System.out.println(1);
            return ResultUtils.buildFail("密码太简单了哦");
        }
        System.out.println(password.equals(password2));
       *//* if (password2 == null || "".equals(password2)) {
            System.out.println(2);
            return ResultUtils.buildFail("密码不能为空");
        }*//*
        if (!password.equals(password2)){
            return ResultUtils.buildFail("两次密码不一致");
        }
        if (checkCode==null || "".equals(checkCode)){
            System.out.println(4);
            return ResultUtils.buildFail("验证码不能为空");
        }
        logger.info("邮箱加验证码 : {}",email+checkCode);*/

        String value = (String)request.getSession().getAttribute(email + checkCode);
        logger.info("客户端传来的可以所对应的session的值为 : {}",value);
        System.out.println((email+checkCode).equals(value));
        System.out.println((email+checkCode)==value);
        if (!(email+checkCode).equals(value)){
            return ResultUtils.buildFail("verification code not correct");
        }
        Users user = new Users(username,password, email,checkCode);

        try {
            registerUserService.register(user);
        } catch (CustomException e) {
            e.printStackTrace();
            return ResultUtils.buildFail(e.getMessage());
        }
        request.getSession().removeAttribute(email + checkCode);
        System.out.println("注册-----------");
        return ResultUtils.buildSuccess();
    }
    //修改密码..
    @ResponseBody
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Result updatePassword(String email,String password,String password2,
                                 String checkCode,HttpServletRequest request){
        System.out.println(email+"  "+password+"  "+password2+"  "+checkCode);
        if (email==null || "".equals(email)){
            return ResultUtils.buildFail("illegal Email");
        }
        if (!passwordUtils.isPasswordUtils(password)){
            System.out.println(1);
            return ResultUtils.buildFail("low password safety level");
        }
        if(!password.equals(password2)){
            return ResultUtils.buildFail("Confirmed password not correct");

        }
        if (checkCode == null || "".equals(checkCode)) {
            return ResultUtils.buildFail("verification code can not be empty");
        }
        HttpSession session = request.getSession();
        String newEmailCheckCode = (String) session.getAttribute(email+checkCode);
        session.removeAttribute(email+checkCode);
//        String newEmailCheckCode = (String)request.getSession().getAttribute(email+checkCode);
        System.out.println(newEmailCheckCode);
        System.out.println(email+checkCode);
        if (!(email+checkCode).equals(newEmailCheckCode)){
            return ResultUtils.buildFail("verification code not correct");
        }
        Integer update = registerUserService.update(email, password);
        if (update !=0){
            return ResultUtils.buildSuccess();
        }
        System.out.println("---------");
        return ResultUtils.buildFail("Fail to alter password");

    }
    //修改密码发送邮箱
    @RequestMapping("/alter")
    public Result alterByEmails(String email,HttpServletRequest request) {
        if (request.getSession().getAttribute(email) != null || "".equals(request.getSession().getAttribute(email))){
            request.getSession().removeAttribute(email);
            return ResultUtils.buildFail("Please wait 30 seconds to continue action");
        }

        System.out.println(email);
        boolean a = emailUtils.isEmail(email);
        System.out.println(a);
        //如果是空的或者不合法，直接返回前端
        if (!emailUtils.isEmail(email)) {
            return ResultUtils.buildFail("illegal Email");
        }
        System.out.println("--------aa");
        //数据库有没有
        List<Users> oneByEmail = registerUserService.findOneByEmail(email);
        if (oneByEmail.size()==0 || oneByEmail.isEmpty()){
            return ResultUtils.buildFail("Email already registered");
        }
        //生成随机的6位验证码
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "your register verification code is："+checkCode;
        logger.info("your register verification code is : {}",checkCode);
        HttpSession session = request.getSession();
        session.setAttribute(email+checkCode,email+checkCode);

        session.setMaxInactiveInterval(5*60);
//            Cookie cookie = new Cookie("email"+checkCode,email+checkCode);
//            cookie.setMaxAge(300);
//            response.addCookie(cookie);
        //进来了表示数据库中没有数据
        System.out.println(email);
        System.out.println(message);
        registerUserService.sendSimpleMail(email,"睿讯验证码",message);

        String replace = UUID.randomUUID().toString().replace("-", "");
        HttpSession session2 = request.getSession();
        session2.setAttribute(email,replace);
        session2.setMaxInactiveInterval(30);
        return ResultUtils.buildSuccess();
        //返回给前端
    }



//    @RequestMapping("/n")
//    public String usernaem(){
//        return "username";
//    }
//    @RequestMapping("/e")
//    public String email(){
//        return "email";
//    }
}
