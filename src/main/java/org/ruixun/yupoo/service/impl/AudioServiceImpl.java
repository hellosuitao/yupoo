package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Audio;
import org.ruixun.yupoo.bean.Picture;
import org.ruixun.yupoo.dao.AudioDao;
import org.ruixun.yupoo.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * 作者：随涛
 * 音频类service
 * */
@Service
@Transactional
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDao audioDao;

    @Override
    public String addAudio(MultipartFile fileaudio) {
        try {
            /*减容量*/
            String originalFilename = fileaudio.getOriginalFilename();
            String name = fileaudio.getOriginalFilename().substring(0, originalFilename.indexOf("."));
            String wei = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
            String newPicName = UUID.randomUUID().toString().replaceAll("-", "") + wei;
            String path = "D:\\image\\" + newPicName;
            File newFile = new File(path);
            fileaudio.transferTo(newFile);
            Audio audio = new Audio();
            audio.setAid(0l);
            audio.setName(name);
            audio.setPath("image.yupoo.com8080/" + newPicName);
            audio.setAsize(fileaudio.getSize());
            audio.setUpdateTime(new Date());
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
    public void deleteAudioById(Long id) {
        audioDao.deleteById(id);
    }
}
