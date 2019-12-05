package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.PicStatu;

import java.util.List;

/*
 * 作者：罗闽秋*/
public interface PicStatuService {
    //根据Pid获取商品信息
    PicStatu findByPid(Long pid);
    //点赞数up
    String likenumup(Long pid);
    //点赞数down
    String likeNumDown(Long pid);
    //浏览数up
    String loonumUp(Long pid);
    //删除商品根据Pids
    String delByPids(List<Long> ids);
    //查询多个商品信息
    List<PicStatu> findPicStatuByPid(List<Album> albums);
    //根据pid删除单个图片
    String delByPid(Long pid);
    //根据ID自定义点赞数，浏览数
    String updateLuo(PicStatu picStatu);
    //根据Id点赞数自增
    String shareUp(Long pid);
}
