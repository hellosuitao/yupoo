package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
作者：suitao
*/
public interface PictureDao extends JpaRepository<Picture,Long> {/*图片dao*/

    Picture findPictureById(Long id);/*根据id查询图片*/

    List<Picture> findPicturesByAid(Long aid);/*根据相册id查图片*/

    List<Picture> findPicturesByAidInAndNameLike(List<Long> ids,String name);/*根据图片名模糊查询图片*/

    List<Picture> findPicturesByAidOrderByNameDesc(Long aid);/*根据图片名模糊查询图片*/
    List<Picture> findPicturesByAidOrderByPsizeDesc(Long aid);/*根据图片大小模糊查询图片*/
    List<Picture> findPicturesByAidOrderByUploadTimeDesc(Long aid);/*根据上传时间模糊查询图片*/

    @Modifying
    void deleteByIdIn(List<Long> ids);

    @Modifying
    void deleteByAidIn(List<Long> aids);

    @Modifying
    @Query("UPDATE Picture p SET p.aid = ?1 WHERE p.path = ?2")
    void setAid(Long aid,String s);

    @Modifying
    void deleteByAid(Long aid);

    @Modifying
    void deleteById(Long id);

    Picture findPictureByPath(String path);
}
