package org.ruixun.yupoo.dao;

import java.util.List;
import org.ruixun.yupoo.bean.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
    登录
    作者：李万君
 */
@Repository
public interface LoginUserDao extends JpaRepository<Users,Long> {

    /*
        根据 用户名和密码 去判断数据库中有没有
     */
    List<Users> findUserByEmailAndPassword(String email, String password);
    /*
        根据 邮箱查询 用户权限
     */
    List<Users> findUserByEmail(String emain);
    /*
        修改密码
     */
    @Modifying
    @Transactional
    @Query("UPDATE Users SET password=?1 WHERE email=?2 AND password=?3")
    int update(String password2,String email,String password);
}
