package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.Audio;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author suitao
 * @description 音频service
 */
public interface AudioService {
    String addAudio(MultipartFile file);//根据商品id添加视频

    List<Audio> findAudioById(Long valueOf);//根据相册id查音频

    void deleteAudioById(Long id);
}
