package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
* 作者：suitao*/
public interface PictureService {/*图片接口*/

    Picture findPictureById(Long id);/*根据id查图片*/

    List<Picture> findPicturesByAid(Long aid);/*根据商品id查图片*/

    void deleteById(Long id,Long userId);/*删除图片*/

    List<Picture> findPicturesByAidInAndNameLike(List<Long> ids,String name);/*根据图片名模糊查询图片*/

    void deleteByIds(List<Long> albumsIds);

    void deleteByAids(List<Long> aids);

    String setWaterMark(MultipartFile[] file,List<Long> picIds);
}
