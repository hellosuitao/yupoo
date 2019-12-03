package org.ruixun.yupoo.bean;

import javax.persistence.*;
import java.io.Serializable;

/*
    用户表和角色表的中间表：
    作者：李万君
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "u_id")
    private Long uId;
    @Column(name = "r_id")
    private Long rId;

    public UserRole(Long uId, Long rId) {
        this.uId = uId;
        this.rId = rId;
    }

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", uId=" + uId +
                ", rId=" + rId +
                '}';
    }
}
