package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/*
* 作者：随涛*/
public interface AlbumService {/*相册接口*/

    List<Album> findAlbumsByUid(Long id);/*根据用户id查询相册*/

    void addAlbum(Album album);/*添加相册*/

    Album findAlbumById(Long valueOf);/*根据相册id查相册*/

    void setCoverpath(Long id,String coverpath);/*根据相册id修改封面路径*/

    Page<Album> findAlbumsByUidAndNameLike(String name);/*根据相册名模糊查询相册*/

    void setSortalbum(long uid, String sortoption);/*修改相册排序方式*/

    void setSortpicture(long id, String sortoption);/*修改图片排序方式*/

    void deleteByAid(Long aid);

    //后台搜索
    Page<Album> findAlbumsByCondition2(String time,Long categoryId,String selectBy,String inputValue,Integer page,Integer size,Long userId);//条件查询
    //前台搜索
    Page<Album> findAlbumsByCondition(String time, String categoryId, String albumName,Integer page,Integer size);

    void deleteByIds(List<Long> ids);//批量删除

    Page<Album> findAll(Integer page,Integer size,String condition,String albumCategory,Long userId);//分页查询

    void upshelf(List<Long> ids);//批量上架

    void downshelf(List<Long> ids);//批量上架

    void updateAlbum(Album album);
}
