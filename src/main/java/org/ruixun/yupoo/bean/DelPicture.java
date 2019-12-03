package org.ruixun.yupoo.bean;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 * 作者：罗闽秋*/
@Entity
@Table(name = "del_picture")
public class DelPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    private  Long aid;

    @NotNull
    private  Long pid;

    @NotEmpty
    private  String name;

    @NotEmpty
    private  String path;

    @NotNull
    private  Long psize;


    @Column(name = "upload_time")
    private  Date uploadTime;


    @Column(name = "update_time")
    private  Date updateTime;

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", aid=" + aid +
                ", pid=" +pid+
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", psize=" + psize +
                ",uploadTime=" +  uploadTime +
                ",updateTime=" + updateTime +
                '}';
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getPsize() {
        return psize;
    }

    public void setPsize(Long psize) {
        this.psize = psize;
    }

    public DelPicture() {
    }

    public DelPicture(Long id,Long aid,Long pid, String name, String path, Long psize, Date uploadTime, Date updateTime) {
        this.id=id;
        this.aid = aid;
        this.pid=pid;
        this.name = name;
        this.path = path;
        this.psize = psize;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
    }
}
