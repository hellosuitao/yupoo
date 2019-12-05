package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Audio;
import org.ruixun.yupoo.bean.DelAudio;
import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.dao.AudioDao;
import org.ruixun.yupoo.dao.DelAudioDao;
import org.ruixun.yupoo.service.DelAudioService;
import org.ruixun.yupoo.utils.StaticProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author suitao
 * @description
 */
@Service
@Transactional
public class DelAudioServiceImpl implements DelAudioService {

    @Autowired
    private DelAudioDao delAudioDao;
    @Autowired
    private StaticProperties staticProperties;
    @Autowired
    private AudioDao audioDao;

    @Override
    public void deleteById(Long id) {
        DelAudio delAudio = delAudioDao.getOne(id);
        String path = delAudio.getPath();
        String name = path.split(staticProperties.getStaticport())[1];
        String filePath = staticProperties.getPicturepath()+name;
        File file = new File(filePath);
        file.delete();
        delAudioDao.deleteById(id);
    }

    @Override
    public Page<DelAudio> findAll(Integer page, Integer size, Long userId) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "updateTime");
        Page<DelAudio> all = delAudioDao.findAllByUserId(pageable,userId);
        return all;
    }

    @Override
    public List<DelAudio> findAllByIds(List<Long> list) {
        return delAudioDao.findAllByIdIn(list);
    }

    @Override
    public String recover(List<Long> list) {
        List<DelAudio> delAudios = delAudioDao.findAllByIdIn(list);
        if(delAudios!=null&&delAudios.size()>0){
            delAudios.forEach(audio -> {
                audioDao.save(new Audio(audio.getAid(),audio.getName(),audio.getPath(),audio.getAsize(),new Date(),null,audio.getUserId()));
            });
            delAudioDao.deleteAllByIdIn(list);
        }
        return "success";
    }
}
