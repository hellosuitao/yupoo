package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.DelPicture;
import org.ruixun.yupoo.bean.Picture;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/*
 * 作者：罗闽秋*/
public interface DelPictureService {
    //分页查询回收站所有图片
    Page<DelPicture> findAll(Integer page,Integer size);
    Page<DelPicture> findAll(Integer page,Integer size,Long userId);
    //获取回收站全部图片信息
    List<DelPicture> findAllDelPic();
    //根据Id删除回收站的图片
    String delDelpic(Long pid);
    //根据多个Id查询图片
    List<DelPicture> findByPids(List<Long> pids);
    //清空回收站
    String delAll();
    //批量删除
    String delByPids(List<Long> pids);
    //对回收站进行检查删除
    String checkDel();
    //恢复图片
    String  insertPic(List<DelPicture> delpictures);
}
