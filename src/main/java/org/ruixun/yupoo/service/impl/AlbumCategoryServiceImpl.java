package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.AlbumCategory;
import org.ruixun.yupoo.dao.AlbumCategoryDao;
import org.ruixun.yupoo.service.AlbumCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumCategoryServiceImpl implements AlbumCategoryService {
    @Autowired
    private AlbumCategoryDao albumCategoryDao;

    @Override
    public AlbumCategory findAlbumCategoryById(Long id) {
        return albumCategoryDao.findAlbumCategoryById(id);
    }

    @Override
    public Long saveAlbumCategory(AlbumCategory albumCategory) {
//        albumCategory.setLocationId(albumCategoryDao.findMaxLocationID()+1L);
        albumCategoryDao.save(albumCategory);
        return albumCategory.getId();
    }

    @Override
    public List<AlbumCategory> findAll() {
        return albumCategoryDao.findAll();
    }

    @Override
    public Integer updateOrder(Long locationId, Long id) {

        return albumCategoryDao.updateLocationId(locationId, id);
    }

    @Override
    public void delectTable(Long albumCategoryId) {
        albumCategoryDao.deleteById(albumCategoryId);
    }



}
