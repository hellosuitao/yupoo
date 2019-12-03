package org.ruixun.yupoo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
    过滤器
    作者： 李万君 暂时没用
 */
@WebFilter(filterName = "customFilter",urlPatterns = "/*")
//@Component
public class CustomFilter implements Filter {
    private static final Logger logger =
            LoggerFactory.getLogger(CustomFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("==============初始化过滤器=============");
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
        logger.info("===========处理请求==========");
        HttpServletRequest request=(HttpServletRequest)rq;
        HttpServletResponse response= (HttpServletResponse)rp;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        logger.info("aa : {}",request.getRequestURI());

    }

    @Override
    public void destroy() {

    }
}
