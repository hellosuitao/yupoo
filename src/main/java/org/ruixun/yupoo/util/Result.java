package org.ruixun.yupoo.util;

public class Result<T> {
    private String status;
    private String code;
    private String message;
    private T data;
    public Result() {
    }

    public Result(String status, String code, String message, T date) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T date) {
        this.data = date;
    }
}
