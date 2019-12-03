package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.AlbumCategory;

import java.util.List;
/***
 *
 */
public interface AlbumCategoryService {

    AlbumCategory findAlbumCategoryById(Long id);
    //新增
    Long saveAlbumCategory(AlbumCategory albumCategory);
    //查询倒叙所有分类
    List<AlbumCategory> findAll();

    Integer updateOrder(Long locationId,Long id);
    //删除分类
    void delectTable(Long albumCategoryId);
}
