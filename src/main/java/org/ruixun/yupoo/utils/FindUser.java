package org.ruixun.yupoo.utils;

import org.ruixun.yupoo.bean.Users;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author suitao
 * @description
 */
public class FindUser {
    public static Users findUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sesionName = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    sesionName = cookie.getValue();
                    System.out.println(sesionName);
                }
            }
            Users user = (Users) request.getSession().getAttribute(sesionName);
            return user;
        }
        return null;
    }
}
