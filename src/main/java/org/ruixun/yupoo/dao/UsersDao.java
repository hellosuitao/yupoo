package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.Users;
import org.springframework.data.jpa.repository.JpaRepository;
/*
   根据邮箱去查询 用户的信息
   作者：李万君
 */
public interface UsersDao extends JpaRepository<Users,Long> {
   Users findUserByEmail(String email);

}
