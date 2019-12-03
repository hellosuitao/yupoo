package org.ruixun.yupoo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
    邮箱的校验
    作者：李万君
 */
@Component
public class EmailUtils {
    //邮箱的简单的合法校验
    public boolean isEmail(String email){
        if (email == null || email.length()==0){
            return false;
        }
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        //编辑模板
        Pattern p = Pattern.compile(str);
        //查看传来的email是否符合
        Matcher m = p.matcher(email);
        if (m.matches()){
            return true;
        }
        return false;
    }

    //发送端 邮件的名字 83862072@qq.com
    @Value("${spring.mail.username}")
    private String form;
    @Autowired
    private JavaMailSender javaMailSender;

    //邮箱注册
    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage message=new SimpleMailMessage();
        //发送端邮箱
        message.setFrom(form);
        //接收端邮箱
        message.setTo(to);
        //标题
        message.setSubject(title);
        //内容
        message.setText(content);
        javaMailSender.send(message);

    }


}

