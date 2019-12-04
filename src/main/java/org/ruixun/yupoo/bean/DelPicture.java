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
    private  Long aid;//商品id

    @NotNull
    private  Long pid;//图片id

    @NotEmpty
    private  String name;

    @NotEmpty
    private  String path;

    @NotNull
    private  Long psize;

    private Long userId;


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

    @Override
    public String toString() {
        return "DelPicture{" +
                "id=" + id +
                ", aid=" + aid +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", psize=" + psize +
                ", userId=" + userId +
                ", uploadTime=" + uploadTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DelPicture(@NotNull Long aid, @NotNull Long pid, @NotEmpty String name, @NotEmpty String path, @NotNull Long psize, Long userId, Date uploadTime, Date updateTime) {
        this.aid = aid;
        this.pid = pid;
        this.name = name;
        this.path = path;
        this.psize = psize;
        this.userId = userId;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
    }
}
