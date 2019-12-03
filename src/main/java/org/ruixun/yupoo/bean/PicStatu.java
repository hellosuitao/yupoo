package org.ruixun.yupoo.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
 * 作者：罗闽秋*/
@Entity
@Table(name = "pic_statu")
public class PicStatu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;

    private  Long pid;

    @NotNull
    private  Long upnum;
    @NotNull
    private  Long looknum;

    public PicStatu() {
    }

    public PicStatu(Long id,Long pid, Long upnum, Long looknum) {
        this.id=id;
        this.pid = pid;
        this.upnum = upnum;
        this.looknum = looknum;
    }

    public Long getId() {
        return id;
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

    public Long getUpnum() {
        if(upnum==null){
            return 0l;
        }
        return upnum;
    }

    public void setUpnum(Long upnum) {
        this.upnum = upnum;
    }

    public Long getLooknum() {
        if(looknum==null){
            return 0l;
        }
        return looknum;
    }

    public void setLooknum(Long looknum) {
        this.looknum = looknum;
    }

    @Override
    public String toString() {
        return "PicStatu{" +
                "id=" + id +
                ", pid=" + pid +
                ", upnum=" + upnum +
                ", looknum=" + looknum +
                '}';
    }
}
