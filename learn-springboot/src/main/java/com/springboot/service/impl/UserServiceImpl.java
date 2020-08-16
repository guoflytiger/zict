package com.springboot.service.impl;

import com.springboot.entity.User;
import com.springboot.repository.UserRepository;
import com.springboot.service.UserService;
import com.springboot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public int saveUserLoginNum() {
        String userLoginNum = (String) redisUtils.get("userLoginNum");
        if (userLoginNum == null){
            redisUtils.set("userLoginNum","1");
        }else {
            redisUtils.incr("userLoginNum",1);
        }
        return 0;
    }

    @Override
    public User findUserByUserNameAndPwd(String userName, String passWord) {
        return userRepository.findUserByUserNameAndPassWord(userName,passWord);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }
}
