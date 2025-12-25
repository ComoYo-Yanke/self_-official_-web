package com.comoyo.service;


import com.comoyo.pojo.User;

public interface UserService {
    
    User getUserById(Integer id);
    
    void register(User user);
}
