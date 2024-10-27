package com.xiaowudr.atmsite.mapper;

import com.xiaowudr.atmsite.pojo.Account;
import com.xiaowudr.atmsite.pojo.AccountLock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountLockMapper {
    void insertAccountLock(AccountLock accountLock);

    //int updateAccountLock(AccountLock accountLock);

    AccountLock getAccountLock(@Param("accountId") String accountId);
}
