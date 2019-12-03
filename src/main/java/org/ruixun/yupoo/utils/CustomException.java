package org.ruixun.yupoo.utils;

/*
    自定义异常类
    作者：李万君
 */
public class CustomException extends Exception{


    private String message;

    public CustomException() {
        super();
    }
    public CustomException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
