package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.dao.AlbumDao;
import org.ruixun.yupoo.dao.DelPictureRepository;
import org.ruixun.yupoo.dao.MessagesRepository;
import org.ruixun.yupoo.dao.PictureDao;
import org.ruixun.yupoo.service.DelPictureService;
import org.ruixun.yupoo.service.DelService;
import org.ruixun.yupoo.service.PicStatuService;
import org.ruixun.yupoo.utils.PictureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/*
 * 作者：罗闽秋*/
@Service
@Transactional
public class DelPictureServiceImpl implements DelPictureService {
    @Autowired
    private DelPictureRepository delPictureRepository;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private DelService delService;
    @Autowired
    private AlbumDao albumDao;

    /*
     * 检查回收站照片是否过期
     * 过期进行删除
     * */
    @Override
     public String checkDel(){
         List<DelPicture> allDelPic = delPictureRepository.findAll();
         Long time1 = new Date().getTime();
         List<Long> list = new ArrayList<>();
         for (DelPicture del:allDelPic) {
             Long time = del.getUpdateTime().getTime();
             if (time1-time>86400000*7){
                 list.add(del.getId());
                 delPictureRepository.deleteByPid(del.getPid());
//                 messagesRepository.deleteByPid(del.getPid());
                 delService.delPicByPath(del.getPath());
//                 picStatuService.delByPid(del.getPid());
             }
         }
         return "success";
     }

    @Override
    public Page<DelPicture> findAll(Integer page,Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        Page<DelPicture> all = delPictureRepository.findAll(pageable);
        return all;
    }
    /*suitao*/
    @Override
    public Page<DelPicture> findAll(Integer page,Integer size,Long userId) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        Page<DelPicture> all = delPictureRepository.findAllByUserId(pageable,userId);
        return all;
    }

    //查询回收站所有图片
    @Override
    public List<DelPicture> findAllDelPic() {
        List<DelPicture> pictureList = delPictureRepository.findAll();
        return pictureList;
    }
    //根据图片ID删除图片
    @Override
    public String delDelpic(Long pid) {
        DelPicture byId = delPictureRepository.findDelPicturesByPid(pid);
        delPictureRepository.deleteByPid(pid);
//        messagesRepository.deleteByPid(byId.getPid());
        delService.delPicByPath(byId.getPath());
//        picStatuService.delByPid(byId.getPid());
        return "success";
    }
    //根据Id查询多个图片
    @Override
    public List<DelPicture> findByPids(List<Long> pids) {
        List<DelPicture> delPictures = delPictureRepository.findList(pids);
        return delPictures;
    }

    //清空回收站
    @Override
    public String delAll() {
        List<DelPicture> pic = delPictureRepository.findAll();
        if (pic.isEmpty()){
            return "success";
        }
        ArrayList<Long> list = new ArrayList<>();
        pic.forEach(pi->list.add(pi.getPid()));
        List<String> paths = delPictureRepository.findPathByPids(list);
        delPictureRepository.deleteAll();
//        messagesRepository.deleteByPidIn(list);
        paths.forEach(path->delService.delPicByPath(path));
//        picStatuService.delByPids(list);
        return "success";
    }
    //删除多个图片
    @Override
    public String delByPids(List<Long> pids) {
        List<DelPicture> delPictures = delPictureRepository.findDelPicturesByPidIn(pids);
        List<String> paths = delPictureRepository.findPathByPids(pids);
        System.out.println(pids);
        delPictureRepository.deleteDelPictureByPidIn(pids);
//        messagesRepository.deleteByPidIn(longs);
        paths.forEach(path->delService.delPicByPath(path));
//        picStatuService.delByPids(longs);
        return "success";
    }
    //恢复回收站图片
    @Override
    public String insertPic(List<DelPicture> delpictures) {
        PictureUtils pictureUtils = new PictureUtils();
        delpictures.forEach(delPicture -> delPicture.setUpdateTime(new Date()));
        List<Picture> lists = pictureUtils.DelToPic(delpictures);
        lists.forEach(list->pictureDao.save(list));
        Map<Long, String> map = new HashMap<>();
        delpictures.forEach(delPicture -> {
            if (map.get(delPicture.getAid())==null){
                map.put(delPicture.getAid(),delPicture.getPath());
            }else {
                map.put(delPicture.getAid(),map.get(delPicture.getAid())+","+delPicture.getPath());
            }
        });
        map.keySet().forEach(lo -> {
            String s = map.get(lo)+",";
            Album albumById = albumDao.findAlbumById(lo);
            albumDao.updatePictures(albumById.getPictures()+","+s,lo);
        });
        List<Long> pids=new ArrayList<Long>();
        delpictures.forEach(delPicture -> pids.add(delPicture.getPid()));
        delPictureRepository.deleteDelPictureByPidIn(pids);
        return "success";
    }
}
