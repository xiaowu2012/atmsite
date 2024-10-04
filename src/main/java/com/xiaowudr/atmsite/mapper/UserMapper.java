package com.xiaowudr.atmsite.mapper;


import com.xiaowudr.atmsite.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);
    User getUserByAccount(String account);
}
