package org.ruixun.yupoo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category_type")
public class AlbumCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Length(max = 16,message = "分类名过长")
    @Column(name = "name")
    private String name;
    @Column(name = "is_parent")
    private boolean isParent=false;
    private Long userId;
    private Long parentId=0l;
    @ManyToMany(mappedBy = "albumCategories", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "albumCategories" })
    private List<Album> albums;

    public boolean getIsParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public AlbumCategory(@NotEmpty @Length(max = 16) String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public boolean isParent() {
        return isParent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "AlbumCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                ", parentId=" + parentId +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public AlbumCategory() {
    }
}
