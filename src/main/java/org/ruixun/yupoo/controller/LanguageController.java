package org.ruixun.yupoo.controller;

import freemarker.template.utility.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
public class LanguageController implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String s = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();
        System.out.println("进,locale is + " + s + ",语言选择是 is" + s);
        if (!StringUtils.isEmpty(s)){
            String[] str = s.split("_");
            locale = new Locale(str[0],str[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
