package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.*;
import org.ruixun.yupoo.dao.*;
import org.ruixun.yupoo.service.*;
import org.ruixun.yupoo.utils.StaticProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * 作者：suitao*/
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {/*相册service*/
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private SurplusCapacityDao surplusCapacityDao;
    @Autowired
    private AlbumCategoryService albumCategoryService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private PicStatuService picStatuService;
    @Autowired
    private DelService delService;
    @Autowired
    private PicStatuRepository picStatuRepository;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private AudioDao audioDao;
    @Autowired
    private StaticProperties staticProperties;
    @Autowired
    private DelPictureRepository delPictureRepository;
    @Autowired
    private DelAudioDao delAudioDao;
    @Autowired
    private DelPictureService delPictureService;
    @Autowired
    private DelAudioService delAudioService;
    @Autowired
    private AudioService audioService;

    @Override
    /*findAll()方法*/
    public List<Album> findAlbumsByUid(Long id) {
        Album album = albumDao.findAlbumsByUid(id).get(0);
        //浏览次数+1
        picStatuService.loonumUp(id);
        String sortcondition = album.getSortalbum();
        if (sortcondition.equals("albumname")) {
            return albumDao.findAlbumsByUidOrderByNameDesc(id);
        } else if (sortcondition.equals("createdate")) {
            return albumDao.findAlbumsByUidOrderByCreateDateDesc(id);
        }/*可以添加自定义排序*/
        return null;
    }

    /*前台 根据条件  查询所有*/
    public Page<Album> findAll(Integer page, Integer size, String condition, String albumCategory, Long userId) {
        if (albumCategory.equals("0")) {
            PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, condition);
            if (userId == 0l) {
                return albumDao.findAll(pageRequest);
            }
            return albumDao.findAllByUid(pageRequest, userId);
        }
        if (!albumCategory.equals("0")) {
            List<AlbumCategory> categories = new ArrayList<>();
            AlbumCategory category = new AlbumCategory();
            category.setId(Long.valueOf(albumCategory));
            categories.add(category);
            PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, condition);
            if (userId == 0l) {
                return albumDao.findAlbumsByAlbumCategories(categories, pageRequest);
            }
            return albumDao.findAllByUidAndAlbumCategories(userId, categories, pageRequest);
        }
        return null;
    }

    @Override
    public void upshelf(List<Long> ids) {
        albumDao.upshelf(ids);
    }

    @Override
    public void downshelf(List<Long> ids) {
        albumDao.downshelf(ids);
    }

    @Override
    public void updateAlbum(Album album) {
        Album oldAlbum = albumDao.findAlbumById(album.getId());
        album.setPictures(oldAlbum.getPictures()+album.getPictures());
        album.setAudios(oldAlbum.getAudios()+album.getAudios());
        album.setUid(1l);
        album.setAsize(oldAlbum.getAsize());
        album.setCreateDate(oldAlbum.getCreateDate());
        albumDao.save(album);
        String[] split = album.getPictures().split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null || split[i] != "") {
                pictureDao.setAid(album.getId(), split[i]);
            }
        }
        String[] split1 = album.getAudios().split(",");
        for (int i = 0; i < split1.length; i++) {
            if (split1[i] != null || split1[i] != "") {
                audioDao.setAid(album.getId(), split1[i]);
            }
        }
        albumDao.save(album);

    }

    @Override
    public void addAlbum(Album album) {
        album.setCoverpath(album.getPictures().trim().substring(0, album.getPictures().indexOf(",")));
        album.setCreateDate(new Date());
        albumDao.save(album);
        String[] split = album.getPictures().split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null || !split[i].equals("")) {
                pictureDao.setAid(album.getId(), split[i]);
            }
        }

        if (album.getAudios() != null && album.getAudios().split(",").length > 0) {
            String[] audios = album.getAudios().split(",");
            for (int i = 0; i < audios.length; i++) {
                if (audios[i] != null || !audios[i].equals("")) {
                    audioDao.setAid(album.getId(), audios[i]);
                }
            }
        }

        List<Picture> fakePictures = pictureDao.findPicturesByAid(0l);
        if (fakePictures!=null&&fakePictures.size() > 0) {
            fakePictures.forEach(picture -> {
                String newPathName = staticProperties.getPicturepath() + picture.getPath().split(staticProperties.getStaticport())[1];
                File file1 = new File(newPathName);
                file1.delete();
            });
        }
        pictureDao.deleteByAid(0l);

        List<Audio> fakeAudios = audioDao.findAllByAid(0l);
        if (fakeAudios!=null&&fakeAudios.size() > 0) {
            fakeAudios.forEach(audio -> {
                String newPathName = staticProperties.getPicturepath() + audio.getPath().split(staticProperties.getStaticport())[1];
                File file1 = new File(newPathName);
                file1.delete();
            });
        }
        audioDao.deleteAllByAid(0l);
        picStatuRepository.save(new PicStatu(null, album.getId(), 0l, 0l, 0l));
    }

    @Override
    public Album findAlbumById(Long id) {
        return albumDao.findAlbumById(id);
    }

    @Override
    public void setCoverpath(Long id, String coverpath) {
        albumDao.setCoverpath(id, coverpath);
    }

    @Override
    public Page<Album> findAlbumsByUidAndNameLike(String name) {
        PageRequest pageRequest = new PageRequest(0, 6);
        return albumDao.findAlbumsByNameLike(name, pageRequest);
    }

    @Override
    public void setSortalbum(long uid, String sortoption) {
        albumDao.setSortalbum(uid, sortoption);
    }

    @Override
    public void setSortpicture(long id, String sortoption) {
        albumDao.setSortpicture(id, sortoption);
    }


    @Override
    public Page<Album> findAlbumsByCondition(String time, String categoryId,
                                             String albumName, Integer page, Integer size) {
        return null;
    }

    //后台条件查询
    public Page<Album> findAlbumsByCondition2(String time, Long categoryId, String inputValue, String selectBy, Integer page, Integer size, Long userId) {/*条件查询*/
        System.out.println(time + "," + categoryId + "," + selectBy + "," + inputValue + "," + page + "," + size + "," + userId);
        //初始化分页
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        //初始化分类
        List<AlbumCategory> categories = new ArrayList<>();
        AlbumCategory category = new AlbumCategory();
        category.setId(categoryId);
        categories.add(category);
        //初始化时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, -Integer.valueOf(time));
        Date beforeTime = instance.getTime();
        /*开始查询*/
        //只根据商品名查
        if (categoryId == 0l && selectBy.equals("0") && time.equals("0") && inputValue.equals("")) {
//            return albumDao.findAll(pageRequest);
            return albumDao.findAllByUid(pageRequest, userId);
        }
        if (categoryId == 0l && selectBy.equals("0") && inputValue.equals("")) {
//            return albumDao.findAlbumsByCreateDateAfter(beforeTime, pageRequest);
            return albumDao.findAllByUidAndCreateDateAfter(userId, beforeTime, pageRequest);
        }
        if (categoryId == 0l && selectBy.equals("0")) {
            //查询所有
            if (time.equals("0")) {
//                return albumDao.findAlbumsByNameLike('%' + inputValue + '%', pageRequest);
                return albumDao.findAllByUidAndNameLike(userId, '%' + inputValue + '%', pageRequest);
            }
            //时间查询
            return albumDao.findAllByUidAndNameLikeAndCreateDateAfter(userId, '%' + inputValue + '%', beforeTime, pageRequest);
        }
        if (categoryId == 0l && selectBy.equals("albumName")) {
            if (time.equals("0")) {
                System.out.println("hello===================================");
                return albumDao.findAllByUidAndNameLike(userId, '%' + inputValue + '%', pageRequest);
            }
            return albumDao.findAllByUidAndNameLikeAndCreateDateAfter(userId, '%' + inputValue + '%', beforeTime, pageRequest);
        }
//      前端单选 0：查询全部，1：近三天，7：近一周，30：近一月
        //查询全部+分类 查询
        if (categoryId == 0l && !selectBy.equals("albumName")) {
            return albumDao.findAllByUid(pageRequest, userId);
        }
        //根据分类+时间查询  只根据分类查询
        if (categoryId != 0l && !selectBy.equals("albumName")) {
            if (time.equals("0")) {
                return albumDao.findAllByUidAndAlbumCategories(userId, categories, pageRequest);
            }
            return albumDao.findAllByUidAndAlbumCategoriesAndCreateDateAfter(userId, categories, beforeTime, pageRequest);
            //分类 + 商品名模糊查询
        } else if (selectBy.equals("albumName")) {
            if (time.equals("0")) {
                return albumDao.findAllByUidAndAlbumCategoriesAndNameLike(userId, categories, '%' + inputValue + '%', pageRequest);
            }
            return albumDao.findAllByUidAndAlbumCategoriesAndNameLikeAndCreateDateAfter(userId, categories, '%' + inputValue + '%', beforeTime, pageRequest);
            //根据商品名模糊查询
        }
        return null;
    }

    @Override
    public void deleteByAid(Long aid) {
        Album album = albumDao.findAlbumById(aid);
        /*批量删除评论*/
        List<Long> ids = new ArrayList<>();
        ids.add(aid);
        messagesService.delMessages(ids);
        /*批量删除点赞*/
        picStatuService.delByPids(ids);
        /*删除数据库图片*/
        List<Picture> pictures = pictureService.findPicturesByAid(aid);
        if (pictures != null && pictures.size() > 0) {
            pictures.forEach(picture -> {
                pictureService.deleteById(picture.getId(),album.getUid());
            });
        }
        /*删除回收站图片*/
        List<DelPicture> delPictures = delPictureRepository.findByAid(album.getId());
        List<Long> pids = new ArrayList<>();
        if (delPictures != null && delPictures.size() > 0) {
            delPictures.forEach(delPicture -> {
                pids.add(delPicture.getPid());
            });
        }
        delPictureService.delByPids(pids);
        /*删除视频*/
        List<Audio> audios = audioDao.findAllByAid(aid);
        if (audios != null && audios.size() > 0) {
            audios.forEach(audio -> {
                audioService.deleteAudioById(audio.getId(),album.getUid());
            });
        }
        /*删除回收站视频*/
        List<DelAudio> delAudios = delAudioDao.findByAid(aid);
        if (delAudios != null && delAudios.size() > 0) {
            delAudios.forEach(delAudio -> {
                delAudioService.deleteByAudioId(delAudio.getAudioId());
            });
        }
        /*批量删除商品*/
        albumDao.deleteById(aid);
    }

    @Override
    //批量删除商品 同时删除图片 视频 评论，点赞，不需要回回收站
    public void deleteByIds(List<Long> ids) {
        if (ids != null && ids.size() > 0) {
            ids.forEach(id -> {
                deleteByAid(id);
            });
        }
    }
}
