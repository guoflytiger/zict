package com.springboot.controller;

import com.springboot.comm.R;
import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public R registUser(@RequestBody User user){
        try{
            if (userService.findUserByUserName(user.getUserName()) != null){
                return new R<User>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名已经被注册",null);
            }
            userService.registUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return new R<User>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return R.builder().data(user)
                .code(HttpStatus.OK.value())
                .msg("注册成功!").build();
    }

    @GetMapping("/get/{id}")
    public R findUserById(@PathVariable Integer id){
        User user = null;
        try{
            user = userService.findUserById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new R<User>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return R.builder().data(user)
                .code(HttpStatus.OK.value())
                .msg("查询成功!").build();
    }

    @PostMapping("/login")
    public R login(@RequestBody User user){
        User oldUser = null;
        try{
            oldUser = userService.findUserByUserNameAndPwd(user.getUserName(),user.getPassWord());
            if (oldUser != null){
                userService.saveUserLoginNum();
            }else {
                return new R<User>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"用户名或密码错误!",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new R<User>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
        }
        return R.builder().data(oldUser)
                .code(HttpStatus.OK.value())
                .msg("登陆成功!").build();
    }
}
