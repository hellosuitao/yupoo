package org.ruixun.yupoo.dao;

import java.util.List;
import org.ruixun.yupoo.bean.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
    注册 dao层
    作者：李万君
 */
@Repository
public interface RegisterUserDao extends JpaRepository<Users,Long> {
    /*
        根据用户名 去数据库判断数据是否重复
     */
    @Query("select u from Users u where u.username=?1")
    List<Users> findOneByusername(String username);

    /*
        根据邮箱 去数据库判断数据是否重复
     */
    @Query("select u from Users u where u.email=?1")
    List<Users> findOneByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Users set password=?2,salt=?3 where email=?1")
    Integer update(String email,String password,String stal);
}
