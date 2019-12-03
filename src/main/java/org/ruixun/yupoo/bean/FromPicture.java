package org.ruixun.yupoo.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class FromPicture {

    private Long id;

    private Long aid;

    @NotEmpty
    private String name;
    @NotEmpty
    private String  path;

    @NotNull
    private Long psize;

    private Date updateTime;

    private Date uploadTime;

    @NotNull
    private Long looknum;

    @NotNull
    private Long uonum;

    private List<Mess> mess;

    public FromPicture() {
    }

    public FromPicture(Long id, Long aid, String name, String path, Long psize, Date updateTime, Date uploadTime, Long looknum, Long uonum, List<Mess> mess) {
        this.id = id;
        this.aid = aid;
        this.name = name;
        this.path = path;
        this.psize = psize;
        this.updateTime = updateTime;
        this.uploadTime = uploadTime;
        this.looknum = looknum;
        this.uonum = uonum;
        this.mess = mess;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getLooknum() {
        return looknum;
    }

    public void setLooknum(Long looknum) {
        this.looknum = looknum;
    }

    public Long getUonum() {
        return uonum;
    }

    public void setUonum(Long uonum) {
        this.uonum = uonum;
    }

    public List<Mess> getMess() {
        return mess;
    }

    public void setMess(List<Mess> mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "FromPicture{" +
                "id=" + id +
                ", aid=" + aid +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", psize=" + psize +
                ", updateTime=" + updateTime +
                ", uploadTime=" + uploadTime +
                ", looknum=" + looknum +
                ", uonum=" + uonum +
                ", mess=" + mess +
                '}';
    }
}
