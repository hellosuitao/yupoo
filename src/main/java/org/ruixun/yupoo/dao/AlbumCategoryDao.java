package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.AlbumCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumCategoryDao extends JpaRepository<AlbumCategory, Long> {
    //根据分类ID查询分类
    AlbumCategory findAlbumCategoryById(Long id);

//    @Query(value = "select  MAX(location_id) from category_type", nativeQuery = true)
//    Long findMaxLocationID();
    //查询所有
    @Query(value = "select  * from category_type", nativeQuery = true)
    List<AlbumCategory> findAll();

    List<AlbumCategory> findAllByUserId(Long userId);

//    @Modifying
//    @Query("delete from category_type where id=?1")
//    void deleteAlbumRelevancy(Long id);
    //删除分类
    void deleteById(Long id);
    //根据父类Id查询分类
    List<AlbumCategory> findAlbumCategoryByParentId(Long parentId);
    //删除此类的所有子元素
    @Modifying
    void deleteByParentId(Long parentId);
    //变成父类
    @Modifying
    @Query("update AlbumCategory a set a.isParent=true where id=:id")
    void updateIsParentById(Long id);
    //变成子类
    @Modifying
    @Query("update AlbumCategory a set a.isParent=false where id=:id")
    void updateNotParentById(Long id);
    @Modifying
    @Query("update AlbumCategory a set a.name=:name where id=:id")
    void updataName(String name, Long id);

    /*查询一级或者二级标签*/
    List<AlbumCategory> findAllByUserIdAndIsParent(Long userId,Boolean yn);

    AlbumCategory findByNameAndUserId(String name,Long userId);

}
