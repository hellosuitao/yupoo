package org.ruixun.yupoo.utils;

import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Configuration
@Component
// 统一异常处理器 ：李万君
@RestControllerAdvice
public class MyWebException {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest request
            , HttpServletResponse response, Exception e, Object handler) throws Exception{
        System.out.println("异常被拦截到了");
        String message=null;

        if (e instanceof CustomException){
            CustomException customException = (CustomException)e;
             message= customException.getMessage();
             return ResultUtils.buildFail(message);
        }else if(e instanceof NoHandlerFoundException){
            e.printStackTrace();
            message="404 "+request.getRequestURI();
            return ResultUtils.buildFail(message);
        }else if(e instanceof MailSendException){
            e.printStackTrace();
            message="Failed to send verification code";
            return ResultUtils.buildFail(message);
        }else{
            e.printStackTrace();
            message=" System busy";
            return ResultUtils.buildFail(message);
        }

    }

}
