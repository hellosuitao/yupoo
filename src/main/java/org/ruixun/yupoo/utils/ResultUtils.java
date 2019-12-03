package org.ruixun.yupoo.utils;

//返回一个 result风格的数据 李万君
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
