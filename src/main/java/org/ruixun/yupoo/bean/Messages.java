package org.ruixun.yupoo.bean;

import javax.persistence.*;
import javax.swing.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotNull//(message = "未知错误")
    private  Long pid;
    @NotEmpty//(message = "姓名不能为空")
    private  String name;
    @NotEmpty//(message = "留言不能为空")
    private  String mes;

    public Messages() {
    }

    public Messages(Long pid, String name, String mes) {
        this.pid = pid;
        this.name = name;
        this.mes = mes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Mesasge{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", mes='" + mes + '\'' +
                '}';
    }
}
