package org.ruixun.yupoo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ruixun.yupoo.bean.Permission;
import org.ruixun.yupoo.bean.RolePermission;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.bean.UserRole;
import org.ruixun.yupoo.dao.*;
import org.ruixun.yupoo.service.LoginUserService;
import org.ruixun.yupoo.utils.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/*
    登录的 service 层
    作者:李万君
 */
@Service
@Transactional
public class LoginUserServiceImpl implements LoginUserService {

    Logger logger = LoggerFactory.getLogger(LoginUserServiceImpl.class);

    @Autowired
    private LoginUserDao loginUserDao;

    @Autowired
    private UsersDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private PermissionDao permissionDao;
    /*
       根据 用户名和密码 去判断数据库中有没有
    */
    public List<Users> findByemail(String email, String password){
        return loginUserDao.findUserByEmailAndPassword(email, password);
    }

    @Override
    public Users login(String email, String password) throws CustomException {
        System.out.println(email+"---------");
        List<Users> userByEmail = loginUserDao.findUserByEmail(email);
        if (userByEmail==null || userByEmail.isEmpty()) {
            throw new CustomException("Email not registerd");
        }
        Users user = userByEmail.get(0);
        String newPassword = DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes());
        if(!user.getPassword().equals(newPassword)){
            throw new CustomException("password not correct");
        }

        return user;
    }
    //根据邮箱去获取 权限
    public List<String> getpermissions(String email) {
        List<Long> permissionIds= new ArrayList<>();
        List<String> permissions =new ArrayList<String>();
        Users userByEmail = userDao.findUserByEmail(email);

        logger.info("userByEmail : {}",userByEmail);

        Long userId = userByEmail.getId();
        List<UserRole> userRolesByUId = userRoleDao.findUserRolesByUId(userId);

        if (userRolesByUId.isEmpty()){
            return null;
        }
        UserRole userRole = userRolesByUId.get(0);
        logger.info("userRole : {}",userRole);

        Long roleId = userRole.getrId();
        List<RolePermission> rolePermissionsByRId = rolePermissionDao.findRolePermissionsByRId(roleId);
        if (rolePermissionsByRId.isEmpty()){
            return null;
        }
        for (RolePermission rolePermission : rolePermissionsByRId) {
            Long permissionId = rolePermission.getpId();
            permissionIds.add(permissionId);
        }
        List<Permission> permissionByPidIn =
                permissionDao.findPermissionByIdIn(permissionIds);

            logger.info("permissionByPidIn : {}",permissionByPidIn);

            for (Permission permission : permissionByPidIn) {

                permissions.add(permission.getPresource());

            }
            return permissions;
    }

    @Override
    public void updateInfo(Users users) {
        userDao.save(users);
    }

    @Override
    public Page<Users> findAllCustomer(Pageable pageable,Users user) {
        if(user.getId()==1l){
            return userDao.findAll(pageable);
        };
        return null;
    }
}
