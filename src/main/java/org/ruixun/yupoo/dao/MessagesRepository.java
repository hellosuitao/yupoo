package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MessagesRepository extends JpaRepository<Messages,Long> {
    //根据图片ID删除所有留言
    @Modifying
    void deleteByPid(Long pid);
    //删除多个图片留言
    @Modifying
    void deleteByPidIn(List<Long> pids);

    //根据商品Id查询商品留言
    List<Messages> findByPid(Long pid);

    //根据商品Id查询留言分页
    Page<Messages> findByPid(Long pid,Pageable pageable);
}
