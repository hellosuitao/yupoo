package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.AlbumCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumCategoryDao extends JpaRepository<AlbumCategory,Long> {

    AlbumCategory findAlbumCategoryById(Long id);
    @Query(value = "select  MAX(location_id) from category_type",nativeQuery = true)
    Long findMaxLocationID();
    @Query(value = "select  * from category_type ORDER BY location_id desc ",nativeQuery = true)
    List<AlbumCategory> findAll();
    @Modifying
    @Query(value = "update  AlbumCategory a  set a.locationId=:locationId where a.id=:id")
    Integer updateLocationId(Long locationId,Long id);
//    @Modifying
//    @Query("delete from category_type where id=?1")
//    void deleteAlbumRelevancy(Long id);

    void deleteById(Long id);
    


 }
