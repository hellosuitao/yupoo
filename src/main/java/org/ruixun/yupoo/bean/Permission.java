package org.ruixun.yupoo.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/*
    对应数据库的 permission 表
    权限表
    作者：李万君
 */
@Entity
@Data
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "presource")
    private String presource;

    public Permission(String presource) {
        this.presource = presource;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPresource() {
        return presource;
    }

    public void setPresource(String presource) {
        this.presource = presource;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", presource='" + presource + '\'' +
                '}';
    }
}
