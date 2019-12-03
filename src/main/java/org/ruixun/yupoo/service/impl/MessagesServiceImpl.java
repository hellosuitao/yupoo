package org.ruixun.yupoo.service.impl;

import org.ruixun.yupoo.bean.Mess;
import org.ruixun.yupoo.bean.Messages;
import org.ruixun.yupoo.dao.MessagesRepository;
import org.ruixun.yupoo.service.MessagesService;
import org.ruixun.yupoo.utils.MessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * 作者：罗闽秋
 * */
@Service
@Transactional
public class MessagesServiceImpl implements MessagesService {
    @Autowired
    private MessagesRepository messageRepository;

    @Override//删除一个商品的留言
    public String delMessage(Long pid) {
        messageRepository.deleteByPid(pid);
        return "success";
    }

    @Override//删除多个商品的留言
    public String delMessages(List<Long> pids) {
        messageRepository.deleteByPidIn(pids);
        return "success";
    }

    @Override//添加留言
    public String insMes(Messages messages) {
        messageRepository.save(messages);
        return "success";
    }

    @Override//根据商品Id获取留言信息
    public List<Mess> findMesByPid(Long pid) {
        MessUtils messUtils = new MessUtils();
        List<Messages> pid1 = messageRepository.findByPid(pid);
        List<Mess> messes = messUtils.messageToMess(pid1);
        return messes;
    }

    @Override
    public Page<Messages> findMes(Long pid,Integer page) {
        Pageable pageable=new PageRequest(page,2, Sort.Direction.DESC,"id");
        Page<Messages> byPid = messageRepository.findByPid(pid,pageable);
        return byPid;
    }
}
