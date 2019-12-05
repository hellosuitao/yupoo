package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Album;
import org.ruixun.yupoo.bean.PicStatu;
import org.ruixun.yupoo.dao.PicStatuRepository;
import org.ruixun.yupoo.service.PicStatuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 * 作者：罗闽秋*/
@Service
@Transactional
public class PicStatuServiceImpl implements PicStatuService {
    @Autowired
    private PicStatuRepository picStatuRepository;
    //根据Pid查询商品状态
    @Override
    public PicStatu findByPid(Long pid) {
        PicStatu picStatusByPid = picStatuRepository.findPicStatusByPid(pid);
        return picStatusByPid;
    }
    @Override
    public String updateLuo(PicStatu picStatu) {
        picStatuRepository.updateLooNumAndUpNum(picStatu.getLooknum(),picStatu.getUpnum(),picStatu.getShare(),picStatu.getPid());
        return "success";
    }
    @Override
    public String shareUp(Long pid) {
        picStatuRepository.shareUp(pid);
        return "success";
    }
    /*
     * 点赞方法
     * */
    @Override
    public String likenumup(Long pid) {
        PicStatu statusByPid = picStatuRepository.findPicStatusByPid(pid);
        Long upnum1 = statusByPid.getUpnum();
        long newNum = 1l + upnum1;
        picStatuRepository.updateUpNum(pid);
        return "success";
    }
    //取消点赞
    @Override
    public String likeNumDown(Long pid) {
        picStatuRepository.likeNumDown(pid);
        return "success";
    }

    /*
     * 浏览数添加
     * */
    @Override
    public String loonumUp(Long pid) {
        PicStatu pid1 = picStatuRepository.findPicStatusByPid(pid);
        Long looknum = pid1.getLooknum();
        Long newLooknum=looknum+1l;
        picStatuRepository.loonumUpNum(pid);
        return "success";
    }

    //删除多个
    @Override
    public String delByPids(List<Long> ids) {
        picStatuRepository.deletePicStatuByPidIn(ids);
        return "success";
    }
    //删除一个
    @Override
    public String delByPid(Long pid) {
        picStatuRepository.deletePicStatuByPid(pid);
        return "success";
    }
    @Override
    public List<PicStatu> findPicStatuByPid(List<Album> albums) {
        ArrayList<PicStatu> picStatus = new ArrayList<>();
        for ( Album a:albums) {
            picStatus.add(picStatuRepository.findPicStatusByPid(a.getId()));
        }
        return picStatus;
    }
}
