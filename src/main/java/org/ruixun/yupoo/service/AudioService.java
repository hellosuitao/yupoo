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
    String addAudio(MultipartFile file,Long userId);//根据商品id添加视频

    List<Audio> findAudioById(Long valueOf);//根据商品id查音频

    void deleteAudioById(Long id,Long userId);//假删除，存入回收站

//    String checkDel(Long userId);//检查过期音频

    void delleteAudioByPath(String path);//删除数据库（文件）真实音频

    String deleteAll(Long id);

    String delByIds(List<Long> list);
}
