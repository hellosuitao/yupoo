package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.DelAudio;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author suitao
 * @description
 */
public interface DelAudioService {

    void deleteById(Long id);//根据id删除音频

    void deleteByAudioId(Long id);

    Page<DelAudio> findAll(Integer page, Integer size, Long userId);//根据用户id查所属音频

    List<DelAudio> findAllByIds(List<Long> list);

    String recover(List<Long> list);
}
