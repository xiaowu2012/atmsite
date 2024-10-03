package com.xiaowudr.myuploader.service;

import com.xiaowudr.myuploader.mapper.AccountTimeMapper;
import com.xiaowudr.myuploader.pojo.AccountTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTimeService {
    @Autowired
    private AccountTimeMapper accountTimeMapper;

    public void insertAccountTime(AccountTime accountTime) {
        accountTimeMapper.insertAccountTime(accountTime);
    }

    // 调用存储过程服务，传入计算后的money参数
    public void addUserAccountTime(String account, int days) {
        int money = days * 68;  // 将days转换为对应的金额
        accountTimeMapper.callAddUserAccountTime(account, money);
    }
}
