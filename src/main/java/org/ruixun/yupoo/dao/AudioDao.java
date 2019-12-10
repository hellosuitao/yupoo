package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Audio;
import org.ruixun.yupoo.bean.DelAudio;
import org.ruixun.yupoo.bean.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 作者：suitao
* 音频类dao
* */
public interface AudioDao extends JpaRepository<Audio,Long> {

    List<Audio> findAllByAid(Long aid);//根据商品id查音频

    @Modifying
    void deleteAllByAid(Long aid);
    @Modifying
    @Query("UPDATE Audio a SET a.aid = ?1 WHERE a.path = ?2")
    void setAid(Long id, String audio);/*设置所属商品*/

    void deleteByAidIn(List<Long> aids);



}
