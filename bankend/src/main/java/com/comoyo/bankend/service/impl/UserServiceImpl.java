package com.comoyo.bankend.service.impl;

import com.comoyo.bankend.mapper.UserMapper;
import com.comoyo.bankend.service.UserService;
import com.comoyo.bankend.pojo.User;
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
