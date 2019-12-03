package org.ruixun.yupoo.bean;

import javax.persistence.*;
import java.io.Serializable;

/*
    角色表和权限表的中间表
    作者：李万君
 */
@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "r_id")
    private Long rId;
    @Column(name = "p_Id")
    private Long pId;

    public RolePermission(Long rId, Long pId) {
        this.rId = rId;
        this.pId = pId;
    }

    public RolePermission() {
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

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", rId=" + rId +
                ", pId=" + pId +
                '}';
    }
}
