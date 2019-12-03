package org.ruixun.yupoo.bean;

import javax.validation.constraints.NotEmpty;

public class Mess {
    @NotEmpty
    private  String name;
    @NotEmpty
    private  String message;

    public Mess(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public Mess() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "mess{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
