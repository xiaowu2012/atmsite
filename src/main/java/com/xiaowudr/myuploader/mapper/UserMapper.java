package com.xiaowudr.myuploader.mapper;


import com.xiaowudr.myuploader.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);
    User getUserByAccount(String account);
}
