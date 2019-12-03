package org.ruixun.yupoo.dao;


import org.ruixun.yupoo.bean.PicStatu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 * 作者：罗闽秋*/
public interface PicStatuRepository extends JpaRepository<PicStatu,Long> {
   //根据ID(Pid)获取图片
   PicStatu findPicStatusByPid(Long pid);
   //设置点赞数和浏览数
   @Modifying(clearAutomatically = true)
   @Query("update PicStatu p set p.looknum= ?1,p.upnum= ?2 where  p.pid= ?3")
   void  updateLooNumAndUpNum(Long looknum, Long upnum, Long pid);

   //根据Id点赞
   @Modifying(clearAutomatically = true)
   @Query("update PicStatu p set p.upnum=p.upnum+1 where p.pid=?1")
   void  updateUpNum(Long pid);

   //根据Id取消点赞
   @Modifying(clearAutomatically = true)
   @Query("update PicStatu p set p.upnum=p.upnum-1 where p.pid=?1")
   void  likeNumDown(Long pid);

   //根据Id添加浏览数
   @Modifying(clearAutomatically = true)
   @Query("update PicStatu p set p.looknum=p.looknum+1 where p.pid=?1")
   void  loonumUpNum(Long pid);

   @Modifying
   void deletePicStatuByPidIn(List<Long> pids);

   @Modifying
   void deletePicStatuByPid(Long pid);
}
