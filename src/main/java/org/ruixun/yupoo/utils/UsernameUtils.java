package org.ruixun.yupoo.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
    对用户的用户名做合法验证
    作者：李万君
 */
@Component
public class UsernameUtils {

    public boolean isUsername(String username){
        //判空
        if (username==null){
            return false;
        }
        //判断是否合法
        String str="^[a-z\\d\\u4e00-\\u9fa5][a-z\\d\\u4e00-\\u9fa5_]{0,18}[a-z\\d\\u4e00-\\u9fa5]$";
        //编辑模板
        Pattern p = Pattern.compile(str);
        //查看传来的email是否符合
        Matcher m = p.matcher(username);
        if (m.matches()){
            return true;
        }
        return false;
    }
}
