package org.ruixun.yupoo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
/*
作者：suitao
* */
@Entity
@Table(name = "album")
public class Album{/*商品类*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long asize = 0l;/*相册大小 单位B  已不用*/
    private String coverpath = "/image/im_photo_empty.png";/*封面路径*/
    private Long uid;/*所属用户*/
    @NotEmpty //(message = "商品名不能为空！")
    @Length(min = 1,max = 30)//,message = "商品名的长度不符")
    private String name;/*商品名*/
    private Long open;/*是否上架 0 否 1 是*/
    @NotEmpty
    private String description="";//相册描述  不能用desc  会报错
    @Column(name = "create_date")
    private Date createDate;/*创建时间*/
    @Column(name = "delete_date")
    private Date deleteDate;/*删除时间*/
    @NotEmpty
    @JsonIgnoreProperties(value = { "albums" })
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_category",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<AlbumCategory> albumCategories;/*多对多 分类*/
    /*相册排序方式 默认为时间*/
    private String sortalbum ="createdate";
    /*图片排序方式 默认为时间*/
    private String sortpicture = "createdate";/*当做时间查询*/
    @NotNull
    @Min(0l)/*分*/
    @Max(999999999)/**/
    private double price =0l;//商品价格
    @NotEmpty
    private String pictures;//商品图片

    private String audios;

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", asize=" + asize +
                ", coverpath='" + coverpath + '\'' +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", deleteDate=" + deleteDate +
                ", albumCategories=" + albumCategories +
                ", sortalbum='" + sortalbum + '\'' +
                ", sortpicture='" + sortpicture + '\'' +
                ", price=" + price +
                ", pictures='" + pictures + '\'' +
                ", audios='" + audios + '\'' +
                '}';
    }

    public String getAudios() {
        return audios;
    }

    public void setAudios(String audios) {
        this.audios = audios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAsize() {
        return asize;
    }

    public void setAsize(Long asize) {
        this.asize = asize;
    }

    public String getCoverpath() {
        return coverpath;
    }

    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        if(open==0l){
            return "下架";
        }else {
            return "上架";
        }
    }

    public void setOpen(Long open) {
        this.open = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public List<AlbumCategory> getAlbumCategories() {
        return albumCategories;
    }

    public void setAlbumCategories(List<AlbumCategory> albumCategories) {
        this.albumCategories = albumCategories;
    }

    public String getSortalbum() {
        return sortalbum;
    }

    public void setSortalbum(String sortalbum) {
        this.sortalbum = sortalbum;
    }

    public String getSortpicture() {
        return sortpicture;
    }

    public void setSortpicture(String sortpicture) {
        this.sortpicture = sortpicture;
    }

    public double getPrice() {
        return this.price/100d;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Album() {
    }

    public Album(Long asize, String coverpath, Long uid, String name, Long open, String description, Date createDate, Date deleteDate, List<AlbumCategory> albumCategories, String sortalbum, String sortpicture, double price, String pictures) {
        this.asize = asize;
        this.coverpath = coverpath;
        this.uid = uid;
        this.name = name;
        this.open = open;
        this.description = description;
        this.createDate = createDate;
        this.deleteDate = deleteDate;
        this.albumCategories = albumCategories;
        this.sortalbum = sortalbum;
        this.sortpicture = sortpicture;
        this.price = price;
        this.pictures = pictures;
    }
}
