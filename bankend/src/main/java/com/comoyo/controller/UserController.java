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
    
    @GetMapping("/profile")
    public Result getUserById(Integer id) {
        log.info("查询用户: {}", id);
        return Result.success(userService.getUserById(id));
    }
    
    @PutMapping("/update")
    public Result updateUser(@RequestBody User user){
        log.info("更新用户: {}", user);
        return Result.success();
    }
}
