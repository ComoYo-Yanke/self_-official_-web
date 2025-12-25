package com.comoyo.bankend.mapper;

import com.comoyo.bankend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    
     User gerUserById(Integer id);
    
}
