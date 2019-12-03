package org.ruixun.yupoo.bean;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "role")
/*
    对应数据库的 role表 角色表
    作者：李万君
 */
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "r_id")
    private Long rId;
    @Column(name = "position")
    private String position;

    public Role(Long rId, String position) {
        this.rId = rId;
        this.position = position;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rId=" + rId +
                ", position='" + position + '\'' +
                '}';
    }
}
