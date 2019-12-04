package org.ruixun.yupoo.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
* 作者：suitao*/
@Entity
@Table(name = "picture")
public class Picture implements Serializable {/*图片类*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  Long aid;/*相册id*/
    private  String name;/*相册名*/
    private  String path;/*相册路径*/
    private  Long psize;/*图片大小*/
    @Column(name = "upload_time")
    private Date uploadTime;/*上传时间*/
    @Column(name = "update_time")
    private  Date updateTime;/*修改时间*/

    public Picture() {
    }

    public Picture(Long aid, String name, String path, Long psize, Date uploadTime, Date updateTime) {
        this.aid = aid;
        this.name = name;
        this.path = path;
        this.psize = psize;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", aid=" + aid +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", psize=" + psize +
                ", uploadTime=" + uploadTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
