package com.springboot.service;

import com.springboot.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    void registUser(User user);

    User findUserById(Integer id);

    int saveUserLoginNum();

    User findUserByUserNameAndPwd(String userName, String passWord);

    User findUserByUserName(String userName);
}
