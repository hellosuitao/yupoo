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
    List<AlbumCategory> findAll(Long userId);


    //删除分类
    void delectTable(Long albumCategoryId);
    void deleteTable(List<Long> categoryIds);
    //查询所以子类
    List<AlbumCategory> findAlbumCategoryByParentId(Long id);

    //修改分类名字
    void  updataName(String name, Long id);

    List<AlbumCategory> findAllSecond(Long id, boolean b);

    Boolean similarName(String name,Long userId);

//    void deleteCategory(Long categoryId);
}
