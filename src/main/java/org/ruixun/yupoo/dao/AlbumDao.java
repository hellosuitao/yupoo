package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.AlbumCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/*
作者：suitao
* 相册dao*/
public interface AlbumDao extends JpaRepository<Album,Long>{

//    @Query("select album from Album  where album.uid=:uid")//命名参数：推荐
    /*默认方法*/
    List<Album> findAlbumsByUid(Long uid);/*根据用户id查询所有相册*/

    List<Album> findAlbumsByUidOrderByNameDesc(Long uid);/*根据相册名排序查询*/
    List<Album> findAlbumsByUidOrderByCreateDateDesc(Long uid);/*根据创建时间排序查询*/

    Album findAlbumById(Long id);/*根据相册id查询相册*/

    @Modifying
    @Query("update Album a set a.coverpath=?2 where a.id = ?1")
    void setCoverpath(Long id,String coverpath);/*根据相册id修改封面*/

    List<Album> findAlbumsByNameLike(String name);/*根据相册名模糊查询*/

    @Query("select a from Album a where a.uid=?1 and a.name like ?2")
    List<Album> myFindAlbumsByUidAndNameLike(Long uid,String name);/*根据相册名模糊查询*/

    @Modifying
    @Query("update Album a set a.sortalbum =?2 where a.uid = ?1")
    void setSortalbum(Long uid,String sortalbum);/*设值相册排序方式*/

    @Modifying
    @Query("update Album a set a.sortpicture =?2 where a.id = ?1")
    void setSortpicture(long id, String sortoption);/*设值图片排序方式*/

//    @Query("select a from Album a where ")
//    List<Album> findAlbumsByFenlei(List<AlbumCategory> fenleis);

    @Modifying
    @Query("delete from Album a where a.id in ?1 ")
    void deleteByIds(List<Long> ids);

    List<Album> findByIdIn(List<Long> ids);

    @Modifying
    @Query("update Album a set a.open = 1 where a.id in ?1")
    void upshelf(List<Long> ids);

    @Modifying
    @Query("update Album a set a.open = 0 where a.id in ?1")
    void downshelf(List<Long> ids);

    Page<Album> findAlbumsByAlbumCategories(List<AlbumCategory> albumCategories, Pageable pageable);
    Page<Album> findAllByUidAndAlbumCategories(Long userId,List<AlbumCategory> albumCategories, Pageable pageable);

    Page<Album> findAlbumsByAlbumCategoriesAndCreateDateAfter(List<AlbumCategory> albumCategories, Date date,Pageable pageable);
    Page<Album> findAllByUidAndAlbumCategoriesAndCreateDateAfter(Long userId,List<AlbumCategory> albumCategories, Date date,Pageable pageable);

    Page<Album> findAlbumsByNameLikeAndCreateDateAfter (String name, Date date,Pageable pageable);
    Page<Album> findAllByUidAndNameLikeAndCreateDateAfter(Long userId,String name, Date date,Pageable pageable);

    Page<Album> findAlbumsByAlbumCategoriesAndNameLikeAndCreateDateAfter (List<AlbumCategory> albumCategories,String name, Date date,Pageable pageable);
    Page<Album> findAllByUidAndAlbumCategoriesAndNameLikeAndCreateDateAfter(Long userId,List<AlbumCategory> albumCategories,String name, Date date,Pageable pageable);

    Page<Album> findAlbumsByNameLike(String name,Pageable pageable);
    Page<Album> findAllByUidAndNameLike(Long userId,String name,Pageable pageable);

    Page<Album> findAlbumsByAlbumCategoriesAndNameLike(List<AlbumCategory> albumCategories,String name,Pageable pageable);
    Page<Album> findAllByUidAndAlbumCategoriesAndNameLike(Long userId,List<AlbumCategory> albumCategories,String name,Pageable pageable);


    Page<Album> findAlbumsByCreateDateAfter(Date date,Pageable pageable);
    Page<Album> findAllByUidAndCreateDateAfter(Long userId,Date date,Pageable pageable);

    @Modifying
    @Query("update Album a set a.pictures = ?1 where a.id = ?2")
    void updatePictures(String pictures,Long aid);

    Page<Album> findAllByUid(Pageable pageable,Long userId);

    @Modifying
    @Query("update Album a set a.id = ?1 where a.audios = ?2")
    void updateAudioPath(Long aid, String path);
}
