package com.xiaowudr.atmsite.mapper;


import com.xiaowudr.atmsite.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    void insertUser(Account account);
    Account getUserByAccount(String account);
}
