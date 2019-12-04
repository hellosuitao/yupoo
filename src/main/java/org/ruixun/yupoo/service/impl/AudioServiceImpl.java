package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Audio;
import org.ruixun.yupoo.bean.DelAudio;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.dao.AudioDao;
import org.ruixun.yupoo.dao.DelAudioDao;
import org.ruixun.yupoo.service.AudioService;
import org.ruixun.yupoo.utils.StaticProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*
 * 作者：suitao
 * 音频类service
 * */
@Service
@Transactional
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDao audioDao;
    @Autowired
    private DelAudioDao delAudioDao;
    @Autowired
    private StaticProperties staticProperties;
    @Autowired
    private AudioService audioService;

    @Override
    public String addAudio(MultipartFile fileaudio,Long userId) {
        try {
            /*减容量*/
            String originalFilename = fileaudio.getOriginalFilename();
            String name = fileaudio.getOriginalFilename().substring(0, originalFilename.indexOf("."));
            String wei = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
            String newPicName = UUID.randomUUID().toString().replaceAll("-", "") + wei;
            String path = staticProperties.getPicturepath()+ newPicName;
            File newFile = new File(path);
            fileaudio.transferTo(newFile);
            Audio audio = new Audio();
            audio.setAid(0l);
            audio.setUserId(userId);
            audio.setName(name);
            audio.setPath(staticProperties.getPictureuri()+staticProperties.getStaticport() + newPicName);
            audio.setAsize(fileaudio.getSize());
            audio.setUploadTime(new Date());
            audioDao.save(audio);
            return audio.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Audio> findAudioById(Long aid) {
        return audioDao.findAllByAid(aid);
    }

    @Override
    public void deleteAudioById(Long id,Long aid,Long userId) {
        Audio audio = audioDao.getOne(id);
        DelAudio delAudio = new DelAudio(id,aid,audio.getName(),audio.getPath(),audio.getAsize(),new Date(),null,userId);
        delAudioDao.save(delAudio);
        audioDao.deleteById(id);
    }

    @Override
    public void delleteAudioByPath(String path) {
        String filePath =staticProperties.getPicturepath()+path.split(staticProperties.getStaticport())[1];
        File file = new File(filePath);
        file.delete();
    }

    @Override
    public String deleteAll(Long userId) {
        List<DelAudio> audios = delAudioDao.findAllByUserId(userId);
        if (audios.isEmpty()){
            return "success";
        }
        List<Long> list = new ArrayList<>();
        if(list!=null&&list.size()>0){
            audios.forEach(audio->{
                audioService.delleteAudioByPath(audio.getPath());
            });
            delAudioDao.deleteAllByUserId(userId);
        }
        return "success";
    }

    @Override
    public String delByIds(List<Long> ids) {
        List<Long> longs=new ArrayList<>();
        List<DelAudio> delAudios = delAudioDao.findAllByIdIn(ids);
        if(delAudios!=null&&delAudios.size()>0){
            delAudios.forEach(audio -> {
                String path = staticProperties.getPicturepath()+audio.getPath().split(staticProperties.getStaticport());
            });
            delAudioDao.deleteAllByIdIn(ids);
        }
        return "success";
    }

    @Override
    public String checkDel(Long userId) {
        List<DelAudio> delAudios = delAudioDao.findAllByUserId(userId);
        Long time1 = new Date().getTime();
        List<Long> list = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (DelAudio del:delAudios) {
                Long time = del.getUploadTime().getTime();
                if (time1-time>86400000*7){
                    list.add(del.getId());
                    delAudioDao.deleteById(del.getId());
                    audioService.delleteAudioByPath(del.getPath());
                }
            }
        }
        return "success";
    }
}
