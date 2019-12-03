package org.ruixun.yupoo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/*
    获取cookie 中的value
    作者：李万君

 */
public class CookitUtils  {

    public static Object getCookie(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
