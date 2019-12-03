package org.ruixun.yupoo.dao;

import java.util.List;
import org.ruixun.yupoo.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
   查询用户的 所有权限
   作者：李万君
 */
@Repository
public interface PermissionDao extends JpaRepository<Permission,Long> {

//   List<Permission> findPermissionByPidIn(List<Long> Pids);
   List<Permission> findPermissionByIdIn(List<Long> id);

}
