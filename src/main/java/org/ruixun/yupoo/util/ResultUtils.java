package org.ruixun.yupoo.util;

public class ResultUtils {
    public static <T>Result buildSuccess(T t){
        return new Result<T>("success","20000",null,t);
    }
    public static <T>Result buildSuccess(){
        return new Result<T>("success","20000",null,null);
    }
    public  static <T>Result buildFail(String message){
        return new Result<T>("fail","50000",message,null);
    }
}
