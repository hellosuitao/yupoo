package org.ruixun.yupoo.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 作者：suitao
 * 音频类
* */
@Entity
@Table(name = "delAudio")
public class DelAudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long audioId;//音频id
    private  Long aid;/*所属商品id*/
    private  String name;/*音频名*/
    private  String path;/*音频路径*/
    private  Long asize;/*音频大小*/
    @Column(name = "upload_time")
    private Date uploadTime;/*上传回收站时间*/
    @Column(name = "update_time")
    private  Date updateTime;/*删除时间*/
    private Long userId;

    @Override
    public String toString() {
        return "DelAudio{" +
                "id=" + id +
                ", audioId=" + audioId +
                ", aid=" + aid +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", asize=" + asize +
                ", uploadTime=" + uploadTime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
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

    public Long getAsize() {
        return asize;
    }

    public void setAsize(Long asize) {
        this.asize = asize;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DelAudio() {
    }

    public DelAudio(Long audioId, Long aid, String name, String path, Long asize, Date uploadTime, Date updateTime, Long userId) {
        this.audioId = audioId;
        this.aid = aid;
        this.name = name;
        this.path = path;
        this.asize = asize;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
        this.userId = userId;
    }

    public DelAudio(Long audioId, Long aid, String name, String path, Long asize, Date uploadTime, Date updateTime) {
        this.audioId = audioId;
        this.aid = aid;
        this.name = name;
        this.path = path;
        this.asize = asize;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
    }
}
