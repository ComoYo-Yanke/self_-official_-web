package com.comoyo.service.impl;

import com.comoyo.mapper.UserMapper;
import com.comoyo.service.UserService;
import com.comoyo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id){
        return userMapper.gerUserById(id);
    }

}
