package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.*;
import org.ruixun.yupoo.dao.*;
import org.ruixun.yupoo.service.*;
import org.ruixun.yupoo.utils.StaticProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

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
    public Page<Album> findAll(Integer page, Integer size, String condition, String albumCategory,Long userId) {
        if (albumCategory.equals("0")) {
            PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, condition);
            if(userId==0l){
                return albumDao.findAll(pageRequest);
            }
            return albumDao.findAllByUid(pageRequest,userId);
        }
        if (!albumCategory.equals("0")) {
            List<AlbumCategory> categories = new ArrayList<>();
            AlbumCategory category = new AlbumCategory();
            category.setId(Long.valueOf(albumCategory));
            categories.add(category);
            PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, condition);
            if(userId==0l){
                 return albumDao.findAlbumsByAlbumCategories(categories, pageRequest);
            }
            return albumDao.findAllByUidAndAlbumCategories(userId,categories,pageRequest);
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
        album.setPictures(album.getPictures());
        album.setUid(1l);
        album.setAsize(oldAlbum.getAsize());
        album.setCreateDate(oldAlbum.getCreateDate());
        albumDao.save(album);
        String[] split = album.getPictures().split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null || split[i] != "") {
                album.setCoverpath(split[0]);
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
        System.out.println(album);
//        String[] strings = album.getPictures().split(",");
//        String pictures = "";
//        for (int i = 0; i < strings.length; i++) {
//            if(strings[i]!=null&&!strings[i].equals("")){
//                pictures = pictures + "/image/" + strings[i].split("/image/")[1] + ",";
//            }
//        }
        album.setCoverpath(album.getPictures().trim().substring(0, album.getPictures().indexOf(",")));
        album.setCreateDate(new Date());
        albumDao.save(album);
        String[] split = album.getPictures().split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i] != null || !split[i].equals("")) {
//                album.setCoverpath(split[0]);
                pictureDao.setAid(album.getId(), split[i]);
            }
        }

        if (album.getAudios() != null && album.getAudios().split(",").length > 0) {
            String[] audios = album.getAudios().split(",");
            for (int i = 0; i < audios.length; i++) {
                if (audios[i] != null || !audios[i].equals("")) {
//                album.setCoverpath(split[0]);
                    audioDao.setAid(album.getId(), audios[i]);
                }
            }
        }

        List<Picture> fakePictures = pictureDao.findPicturesByAid(0l);
        if (fakePictures.size()>0) {
            fakePictures.forEach(picture -> {
                String path = picture.getPath();
//                /*target 路径*/
//                String filePath =new File(this.getClass().getResource("/").getPath()).getAbsolutePath()+"\\static"+path;
//                File file = new File(filePath);
//                file.delete();
                /*项目路径*/
                String newPathName = staticProperties.getAudiopath() + path.split(staticProperties.getStaticport())[1];
                File file1 = new File(newPathName);
                file1.delete();
            });
        }
        pictureDao.deleteByAid(0l);

        List<Audio> fakeAudios = audioDao.findAllByAid(0l);
        if (fakeAudios.size()>0) {
            fakeAudios.forEach(audio -> {
                String path = audio.getPath();
//                /*target 路径*/
//                String filePath =new File(this.getClass().getResource("/").getPath()).getAbsolutePath()+"\\static"+path;
//                File file = new File(filePath);
//                file.delete();
                /*项目路径*/
                String newPathName = staticProperties.getAudiopath() + path.split(staticProperties.getStaticport())[1];
                File file1 = new File(newPathName);
                file1.delete();
            });
        }
        audioDao.deleteAllByAid(0l);

        picStatuRepository.save(new PicStatu(null, album.getId(), 0l, 0l,0l));
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
    public Page<Album> findAlbumsByCondition2(String time, Long categoryId, String inputValue, String selectBy, Integer page, Integer size,Long userId) {/*条件查询*/
        System.out.println(time + "," + categoryId + "," + selectBy + "," + inputValue + "," + page + "," + size+","+userId);
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
            return albumDao.findAllByUid(pageRequest,userId);
        }
        if (categoryId == 0l && selectBy.equals("0") && inputValue.equals("")) {
//            return albumDao.findAlbumsByCreateDateAfter(beforeTime, pageRequest);
            return albumDao.findAllByUidAndCreateDateAfter(userId,beforeTime,pageRequest);
        }
        if (categoryId == 0l && selectBy.equals("0")) {
            //查询所有
            if (time.equals("0")) {
//                return albumDao.findAlbumsByNameLike('%' + inputValue + '%', pageRequest);
                return albumDao.findAllByUidAndNameLike(userId,'%' + inputValue + '%', pageRequest);
            }
            //时间查询
            return albumDao.findAllByUidAndNameLikeAndCreateDateAfter(userId,'%' + inputValue + '%', beforeTime, pageRequest);
        }
        if (categoryId == 0l && selectBy.equals("albumName")) {
            if (time.equals("0")) {
                System.out.println("hello===================================");
                return albumDao.findAllByUidAndNameLike(userId,'%' + inputValue + '%', pageRequest);
            }
            return albumDao.findAllByUidAndNameLikeAndCreateDateAfter(userId,'%' + inputValue + '%', beforeTime, pageRequest);
        }
//      前端单选 0：查询全部，1：近三天，7：近一周，30：近一月
        //查询全部+分类 查询
        if (categoryId == 0l && !selectBy.equals("albumName")) {
            return albumDao.findAllByUid(pageRequest,userId);
        }
        //根据分类+时间查询  只根据分类查询
        if (categoryId != 0l && !selectBy.equals("albumName")) {
            if (time.equals("0")) {
                return albumDao.findAllByUidAndAlbumCategories(userId,categories, pageRequest);
            }
            return albumDao.findAllByUidAndAlbumCategoriesAndCreateDateAfter(userId,categories, beforeTime, pageRequest);
            //分类 + 商品名模糊查询
        } else if (selectBy.equals("albumName")) {
            if (time.equals("0")) {
                return albumDao.findAllByUidAndAlbumCategoriesAndNameLike(userId,categories, '%' + inputValue + '%', pageRequest);
            }
            return albumDao.findAllByUidAndAlbumCategoriesAndNameLikeAndCreateDateAfter(userId,categories, '%' + inputValue + '%', beforeTime, pageRequest);
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
        String pictures = album.getPictures();
        String[] paths = pictures.split(",");
        List<String> pathss = Arrays.asList(paths);
        pathss.forEach(onePath -> {
//            String newPathName = new File("").getAbsolutePath() + "\\src\\main\\resources\\static" + onePath;
            String newPathName = staticProperties.getPicturepath()+ onePath.split(staticProperties.getStaticport())[1];
//            String newClassPath = new File(this.getClass().getResource("/").getPath()).getAbsolutePath() + "\\static" + onePath;
            File newFile = new File(newPathName);
            newFile.delete();
//            File newClassFile = new File(newClassPath);
//            newClassFile.delete();
        });
        /*批量删除图片*/
        pictureService.deleteByAids(ids);
        /*删除视频*/
        List<Audio> audios = audioDao.findAllByAid(aid);
        audios.forEach(audio -> {
            String newPath = staticProperties.getPicturepath()+audio.getPath().split(staticProperties.getStaticport())[1];
            File file = new File(newPath);
            file.delete();
        });
        audioDao.deleteAllByAid(aid);

        /*批量删除商品*/
        albumDao.deleteById(aid);
    }

    @Override
    //批量删除商品 同时删除图片 评论，点赞，不需要回回收站
    public void deleteByIds(List<Long> ids) {
        List<Album> albums = albumDao.findByIdIn(ids);
        /*批量删除评论*/
        messagesService.delMessages(ids);
        /*批量删除点赞*/
        picStatuService.delByPids(ids);
        /*删除数据库图片*/
        albums.forEach(album -> {
            String pictures = album.getPictures();
            String[] paths = pictures.split(",");
            List<String> pathss = Arrays.asList(paths);
            pathss.forEach(onePath -> {
                String[] split = onePath.split(staticProperties.getStaticport());
                if (!split.equals("") || split != null) {
                    String newPathName =staticProperties.getPicturepath()+split[1];
                    File newFile = new File(newPathName);
                    newFile.delete();
                }
            });
        });

        /*批量删除图片*/
        pictureService.deleteByAids(ids);
        /*批量删除商品*/
        albumDao.deleteByIds(ids);
    }
}
