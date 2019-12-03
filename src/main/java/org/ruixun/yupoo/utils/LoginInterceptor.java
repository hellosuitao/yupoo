package org.ruixun.yupoo.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
    拦截器 做登录权限
    作者：李万君
 */
@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    //存储不拦截的url
    private static final Set<String> NOT_INTERCEPT_URL= new HashSet<>();
    private static final Set<String> LONGIN_URL= new HashSet<>();
    static{
//       NOT_INTERCEPT_URL.add("/ruixun/login2");
//        NOT_INTERCEPT_URL.add("/login/index");
//        NOT_INTERCEPT_URL.add("/login/log");
//        NOT_INTERCEPT_URL.add("/register/admin");
//        NOT_INTERCEPT_URL.add("/register/submit");
//        NOT_INTERCEPT_URL.add("/error");
//        LONGIN_URL.add("/login/log");
    }

/**
 * 请求执行之前，返回false则不执行请求的controller方法直接返回response，
 * 所以如果是一些不合法的请求可以在这里直接设置response的status然后返回false
 * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        //获取请求的url
     /*   System.out.println("拦截器工作--------");
       String uri = request.getRequestURI();
        System.out.println("uri  "+uri);
        if (NOT_INTERCEPT_URL.contains(uri)){
           System.out.println("不拦截");
           return true;
       }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if("token".equals(cookie.getName())){
                    String value = cookie.getValue();
                    HttpSession session = request.getSession();
                    if (session != null){
                        Users user = (Users)session.getAttribute(value);
                        if (user !=null){
                            return true;
                        }
                    }
                }
            }
        }
            try {
                request.getRequestDispatcher("/ruixun/login2").forward(request, response);
                return true;
            } catch (ServletException e) {
                e.printStackTrace();
            }

        return false;*/
        String uri = request.getRequestURI();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setPath("/");
                System.out.println(cookie.getName());
                if("token".equals(cookie.getName())){
                    String value = cookie.getValue();
                    HttpSession session = request.getSession();
                    if (session != null){
                        List<String> permissions = (List<String>) session.getAttribute("permission"+value);
                        if (permissions!=null &&  permissions.size() != 0){
                            for (String permission : permissions) {
                                if(permission.indexOf(uri) !=-1){
                                    return true;
                                }
                            }   //重置response
                                response.reset();
                                //设置编码格式
                                response.setCharacterEncoding("UTF-8");
                                response.setContentType("application/json;charset=UTF-8");
                                System.out.println("权限不够");
                                //以json格式返回给前端一个提示
                                Result result = ResultUtils.buildFail("权限不够");
                                String returnJson = JsonUtils.objectToJson(result);
                                System.out.println(returnJson);
                                response.getWriter().write(returnJson);
                                return false;
                        }
                    }
                }
            }
        }   //重置response
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            System.out.println("请先登录");
            //以json格式返回给前端一个提示
            Result result = ResultUtils.buildFail("请先登录");
            String returnJson = JsonUtils.objectToJson(result);
            System.out.println(returnJson);
            response.getWriter().write(returnJson);
            return false;

    }
    //在请求完成后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //在视图渲染完成之后调用
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler, Exception ex){

    }

}
