package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.AlbumCategory;
import org.ruixun.yupoo.dao.AlbumCategoryDao;
import org.ruixun.yupoo.dao.AlbumDao;
import org.ruixun.yupoo.service.AlbumCategoryService;
import org.ruixun.yupoo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumCategoryServiceImpl implements AlbumCategoryService {
    @Autowired
    private AlbumCategoryDao albumCategoryDao;
    @Autowired
    private AlbumService albumService;

    //根据ID查询分类
    @Override
    public AlbumCategory findAlbumCategoryById(Long id) {
        return albumCategoryDao.findAlbumCategoryById(id);
    }

    //添加分类
    @Override
    public Long saveAlbumCategory(AlbumCategory albumCategory) {
        if(albumCategory.getParentId()==0l){
            albumCategory.setParent(true);
        }
//        if (!(albumCategory.getParentId() == 0L)) {
//            AlbumCategory albumCategoryById = albumCategoryDao.findAlbumCategoryById(albumCategory.getParentId());
//            if (!albumCategoryById.getIsParent()) {
//                albumCategoryDao.updateIsParentById(albumCategoryById.getId());
//            }
//        }
        albumCategoryDao.save(albumCategory);
        return albumCategory.getId();
    }

    //查询所有分类
    @Override
    public List<AlbumCategory> findAll() {
        return albumCategoryDao.findAll();
    }

    //查询所有分类
    @Override
    public List<AlbumCategory> findAll(Long userId) {
        return albumCategoryDao.findAllByUserId(userId);
    }

    //删除分类
    @Override
    public void delectTable(Long albumCategoryId) {
        /*根据id查分类*/
        AlbumCategory albumCategory = albumCategoryDao.findAlbumCategoryById(albumCategoryId);
        /*是一级分类*/
        if (albumCategory.getIsParent()) {
            /*查找二级分类*/
            List<AlbumCategory> second = albumCategoryDao.findAlbumCategoryByParentId(albumCategory.getId());
            if(second!=null&&second.size()>0){
                second.forEach(sec->{
                    /*查询该分类下的所有商品*/
                    List<Album> albums = sec.getAlbums();
                    albums.forEach(album -> {
                        /*调用删除商品接口*/
                        albumService.deleteByAid(album.getId());
                    });
                    /*删除该分类*/
                    albumCategoryDao.deleteById(sec.getId());
                });
            }
            /*删除一级分类*/
            albumCategoryDao.deleteById(albumCategory.getId());
            /*否则删除该分类*/
        } else {
            List<Album> albums = albumCategory.getAlbums();
            albums.forEach(album -> {
                /*调用删除商品接口*/
                albumService.deleteByAid(album.getId());
            });
            /*删除该分类*/
            albumCategoryDao.deleteById(albumCategory.getId());
        }
    }

    /*批量删除分类*/
    @Override
    public void deleteTable(List<Long> categoryIds) {
        categoryIds.forEach(categoryId->{
            delectTable(categoryId);
        });
    }

    //查询本类下的所有子类
    @Override
    public List<AlbumCategory> findAlbumCategoryByParentId(Long id) {
        return albumCategoryDao.findAlbumCategoryByParentId(id);
    }

    @Override
    public void updataName(String name, Long id) {
        albumCategoryDao.updataName( name, id);
    }

    @Override
    public List<AlbumCategory> findAllSecond(Long id, boolean b) {
        return albumCategoryDao.findAllByUserIdAndIsParent(id,false);
    }
}
