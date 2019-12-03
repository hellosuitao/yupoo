package org.ruixun.yupoo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    response 返回一个json的格式带一个异常信息的方法
    作者：李万君
 */
public class ResponseUtils {

    public static void responseJson(HttpServletResponse response,String message){
        //设置类型
        response.setContentType("application/json:charsert:UTF-8");
        try {
            response.getWriter().write(JsonUtils.objectToJson(message));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json工具类调用异常");
        }

    }
}
