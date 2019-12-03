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
    @Length(max = 16)//,message = "相册名过长")
    @Column(name = "name")
    private String name;
    @Column(name = "location_id")
    private Long locationId;

    public Long getLocationId() {
        return locationId;
    }

    public AlbumCategory(String name, Long locationId) {
        this.name = name;
        this.locationId = locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @ManyToMany(mappedBy = "albumCategories", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "albumCategories" })
    private List<Album> albums;

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
                ", locationId=" + locationId +
                '}';
    }

    public AlbumCategory(String name, String desc) {
        this.name = name;

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
