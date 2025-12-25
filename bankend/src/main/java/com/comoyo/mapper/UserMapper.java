package com.comoyo.mapper;

import com.comoyo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
     
     User gerUserById(Integer id);
    
    void register(User user);
}
