package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
    根据用户表的id去查 角色表的 字段
    作者 李万君
 */
@Repository
public interface UserRoleDao extends CrudRepository<UserRole,Long> {

    List<UserRole> findUserRolesByUId(Long uid);

//    List<UserRole> findUserRolesByRId(Long rid);

//    @Query("SELECT UserRole from UserRole where UserRole.r_id=?1")
//    List<UserRole> findUserRoleByRid(Long r_id);





}