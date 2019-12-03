package org.ruixun.yupoo.service;

import org.ruixun.yupoo.bean.Mess;
import org.ruixun.yupoo.bean.Messages;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MessagesService {
    //删除图片全部留言
    String delMessage(Long pid);
    //删除多个图片留言
    String delMessages(List<Long> pids);
    //添加留言
    String insMes(Messages messages);
    //根据商品Id查询留言
    List<Mess> findMesByPid(Long pid);

    //留言分页
    Page<Messages> findMes(Long pid,Integer page);
}
