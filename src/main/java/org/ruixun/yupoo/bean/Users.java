package org.ruixun.yupoo.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


//@Entitry表示该类是实体类，@Table在MySQL创建的表名，项目一启动就会自动创建，@Data需要引入lombok
/*
    实体类对应数据库
    作者_李万君
 */
@Entity
@Data
@Table(name="user")
/*
    对应数据库的user表，用户信息表
    作者：李万君
 */
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @NotEmpty
    @Column(name = "email")
    private String email;
    @Column(name = "salt")
    private String salt;
    @Transient
    private String password2;
    @Transient
    private String checkCode;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Users(String username, @NotEmpty String password, @NotEmpty String email, String salt, String password2, @NotEmpty String checkCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.password2 = password2;
        this.checkCode = checkCode;
    }

    public Users() {
    }

    public Users(String username, String password, String email, String salt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
