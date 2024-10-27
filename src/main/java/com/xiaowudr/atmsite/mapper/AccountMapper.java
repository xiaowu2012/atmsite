package com.xiaowudr.atmsite.mapper;


import com.xiaowudr.atmsite.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    void insertUser(Account account);
    Account getUserByAccount(String accountID);
    int updateUser(Account account);
    int lockUser(@Param("accountId") String accountId, @Param("lockType") int lockType);

}
