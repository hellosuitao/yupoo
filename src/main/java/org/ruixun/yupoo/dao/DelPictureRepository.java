package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.DelPicture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/*
 * 作者：罗闽秋*/
public interface DelPictureRepository extends JpaRepository<DelPicture,Long> {
    //根据时间查询
    Page<DelPicture> findDelPicturesByUpdateTimeBefore(Date date, Pageable pageable);
    //查询所有进行分页
    Page<DelPicture> findAll(Pageable pageable);
    Page<DelPicture> findAllByAid(Pageable pageable,Long userId);
    //删除所有图片
    @Transactional
    void deleteAll();

    //根据Id删除图片
    @Transactional
    void deleteDelPictureByPidIn(List<Long> pids);

    //根据ID查询多个图片
    List<DelPicture> findDelPicturesByPidIn(List<Long> pids);

    //查询多个图片
    @Query("select a from  DelPicture a where a.pid in(:pids)")
    List<DelPicture> findList(List<Long> pids);

    //根据Id查询图片
    DelPicture findDelPicturesByPid(Long pid);

    //根据Pid删除图片
    void  deleteByPid(Long pid);

    //根据Id查询路径
    @Query("select a.path from  DelPicture a where a.pid in (:pids)")
    List<String> findPathByPids(List<Long> pids);

    Page<DelPicture> findAllByUserId(Pageable pageable,Long userId);

}
