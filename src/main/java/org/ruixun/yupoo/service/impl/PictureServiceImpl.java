package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.dao.AlbumDao;
import org.ruixun.yupoo.dao.DelPictureRepository;
import org.ruixun.yupoo.dao.PictureDao;
import org.ruixun.yupoo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/*
* 作者：随涛*/
@Service
@Transactional
public class PictureServiceImpl implements PictureService {/*图片service*/
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private DelPictureRepository delPictureRepository;
    @Override
    public Picture findPictureById(Long id) {
        return pictureDao.findPictureById(id);
    }

    @Override
    public List<Picture> findPicturesByAid(Long aid) {
        return pictureDao.findPicturesByAid(aid);
//        Album album = albumDao.findAlbumById(aid);
//        String sortcondition = album.getSortpicture();
//        return pictureDao.find
//        if(sortcondition.equals("createdate")){
//            return pictureDao.findPicturesByAidOrderByUploadTimeDesc(aid);
//        }else if(sortcondition.equals("albumname")){
//            return pictureDao.findPicturesByAidOrderByNameDesc(aid);
//        }else if(sortcondition.equals("picsize")){
//            return pictureDao.findPicturesByAidOrderByPsizeDesc(aid);
//        }
    }

    @Override
    public void deleteById(Long id,Long aid) {
        /*回收站*/
        Picture picture = pictureDao.findPictureById(id);
        Album album = albumDao.findAlbumById(aid);
        String pictures = album.getPictures();
        pictures =  pictures.replaceAll(picture.getPath()+",","");
        albumDao.updatePictures(pictures,aid);
        delPictureRepository.save(new DelPicture(null,aid,picture.getId(),picture.getName(),picture.getPath(),picture.getPsize(),picture.getUploadTime(),new Date()));
        pictureDao.deleteById(id);
    }

    @Override
    public List<Picture> findPicturesByAidInAndNameLike(List<Long> ids, String name) {

        return pictureDao.findPicturesByAidInAndNameLike(ids,name);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        pictureDao.deleteByIdIn(ids);
    }

    @Override
    public void deleteByAids(List<Long> aids) {
        pictureDao.deleteByAidIn(aids);
    }
}
