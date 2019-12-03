package org.ruixun.yupoo.service.impl;

import java.util.List;
import java.util.UUID;

import org.ruixun.yupoo.bean.UserRole;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.dao.RegisterUserDao;
import org.ruixun.yupoo.dao.UserRoleDao;
import org.ruixun.yupoo.dao.UsersDao;
import org.ruixun.yupoo.service.RegisterUserService;
import org.ruixun.yupoo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/*
    注册的service 层
    作者：李万君
 */
@Service
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private RegisterUserDao registerUserDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UsernameUtils usernameUtils;
    @Autowired
    private RegisterUserService registerUserService;
    @Autowired
    private PasswordUtils passwordUtils;

    //根据用户名去查询数据
    @Override
    public List<Users> findOneByusername(String username){
        return registerUserDao.findOneByusername(username);
    }

    //根据邮箱查询数据
    @Override
    public List<Users> findOneByEmail(String email) {
        return registerUserDao.findOneByEmail(email);
    }
    //发送邮箱验证码
    public void sendSimpleMail(String to,String title,String content){
        emailUtils.sendSimpleMail(to,title,content);
    }

    //保存 前端传来的数据库信息
    @Override
    public void save(Users user) {
        registerUserDao.save(user);
    }

    //注册
    @Override
    public void register(Users user) throws CustomException {

        String salt = new String(DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().replace("-","").getBytes()));
        String newPassword = new String(DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes()));
        System.out.println("salt  "+salt);
        System.out.println("newpassword  "+newPassword);
        user.setSalt(salt);
        user.setPassword(newPassword);
        Users users = registerUserDao.save(user);
        if (users==null){
            throw new CustomException("Register Failed！！！");
        }
        Long id = users.getId();
//        userRoleDao.save(new UserRole(id,3l));
    }


    // 修改密码
    public Integer update(String email,String password){
        String salt = DigestUtils.md5DigestAsHex(UUID.randomUUID().toString().replace("-","").getBytes());
        String newPassword = new String(DigestUtils.md5DigestAsHex((password + salt).getBytes()));
        Integer rt = registerUserDao.update(email, newPassword, salt);
        return rt;
    }

    //保存权限
    public void save(String email,Long rId){
        Users userByEmail = usersDao.findUserByEmail(email);
        if (userByEmail==null){
            return;
        }
        Long uId = userByEmail.getId();
        userRoleDao.save(new UserRole(uId,rId));
    }
    //校验前端信息
    public void check(Users users) throws CustomException {
        //为空直接返回
        if (users.getEmail() == null || "".equals(users.getEmail())){
            throw new CustomException("Email can not be empty!");
        }
        //如果是空的或者不合法，直接返回前端
        if (!usernameUtils.isUsername(users.getUsername())){
            throw new CustomException("illegal Nick name");
        }
        List<Users> oneByusername = registerUserService.findOneByusername(users.getUsername());
        if (oneByusername.size()!=0 || !oneByusername.isEmpty()){
            //返回给前端
            throw new CustomException("Nick name registered already");
        }
        if (users.getUsername()==null || "".equals(users.getUsername())){
            throw new CustomException("Nick name can not be empty");
        }
        if (!passwordUtils.isPasswordUtils(users.getPassword())){
            System.out.println(1);
            throw new CustomException("low password safty level");
        }

       /* if (password2 == null || "".equals(password2)) {
            System.out.println(2);
            return ResultUtils.buildFail("密码不能为空");
        }*/
        if (!users.getPassword().equals(users.getPassword2())){
            throw new CustomException("Confirm password not correct");
        }
        if (users.getCheckCode()==null || "".equals(users.getCheckCode())){
            throw new CustomException("verification code can not be empty");
        }
    }


}
