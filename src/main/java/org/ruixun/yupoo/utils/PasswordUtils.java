package org.ruixun.yupoo.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
    能匹配的组合为：数字+字母，数字+特殊字符，字母+特殊字符，数字+字母+特殊字符组合，而且不能是纯数字，纯字母，纯特殊字符
    作者：李
 */
@Component
public class PasswordUtils {
    public boolean isPasswordUtils(String password){

        //判空
        if (password==null || "".equals(password)){
            return false;
        }
        //判断是否合法
        String str="^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$";
        //编辑模板
        Pattern p = Pattern.compile(str);
        //查看传来的email是否符合
        Matcher m = p.matcher(password);
        if (m.matches()){
            return true;
        }
        return false;
    }
}
