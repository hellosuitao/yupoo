package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Audio;
import org.ruixun.yupoo.bean.DelAudio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * @author suitao
 * @description
 */

public interface DelAudioDao extends JpaRepository<DelAudio,Long> {
    Page<DelAudio> findAllByUserId(Pageable pageable, Long userId);
    List<DelAudio> findAllByUserId(Long userId);//根据userid查询所有音频

    @Modifying
    void deleteAllByUserId(Long userId);

    List<DelAudio> findAllByAudioIdIn(List<Long> ids);

    @Modifying
    void deleteAllByAudioIdIn(List<Long> ids);

    void deleteByAid(Long aid);
    void deleteByAidIn(List<Long> aids);

    List<DelAudio> findByAid(Long aid);

    DelAudio findByAudioId(Long audioId);
    void deleteByAudioId(Long audioId);
}
