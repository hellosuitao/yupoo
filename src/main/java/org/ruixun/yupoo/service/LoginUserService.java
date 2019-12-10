package org.ruixun.yupoo.service;

import java.util.List;

import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.utils.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginUserService {
    List<Users> findByemail(String email, String password);
    Users login(String email, String password) throws CustomException;
    //根据邮箱获取权限
    List<String> getpermissions(String email) throws CustomException;

    void updateInfo(Users users);

    Page<Users> findAllCustomer(Pageable pageable,Users users);
}
