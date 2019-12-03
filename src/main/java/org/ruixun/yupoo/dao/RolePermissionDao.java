package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/*
    根据角色表的id 去查询 权限表的信息
    作者：李万君
 */
public interface RolePermissionDao extends JpaRepository<RolePermission,Long> {

    List<RolePermission> findRolePermissionsByRId(Long rid);
}
