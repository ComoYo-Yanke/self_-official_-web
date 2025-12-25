package com.comoyo.controller;

import com.comoyo.pojo.User;
import com.comoyo.service.UserService;
import com.comoyo.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    
    @GetMapping
    public Result getUserById(Integer id) {
        log.info("查询用户: {}", id);
        return Result.success(userService.getUserById(id));
    }
    
    @PutMapping
    public Result updateUser(@RequestBody User user){
        log.info("更新用户: {}", user);
        return Result.success();
    }
    
    @PostMapping
    public Result register(@RequestBody User user){
        log.info("注册用户: {}", user);
        userService.register(user);
        return Result.success();
    }
    
    @DeleteMapping
    public Result deleteById(Integer id){
        log.info("根据id删除用户：{}", id);
        
        return Result.success();
    }
}
