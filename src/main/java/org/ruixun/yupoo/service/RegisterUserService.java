package org.ruixun.yupoo.service;

import java.util.List;
import org.ruixun.yupoo.bean.Users;
import org.ruixun.yupoo.utils.CustomException;

public interface RegisterUserService {
    List<Users> findOneByusername(String username);
    List<Users> findOneByEmail(String email);
    void sendSimpleMail(String to,String title,String content);
    void save(Users user);
    void register(Users user) throws CustomException;
    Integer update(String email,String password);
    void check(Users users) throws CustomException;
}
