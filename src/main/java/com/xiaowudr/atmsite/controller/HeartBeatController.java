package com.xiaowudr.atmsite.controller;

import com.xiaowudr.atmsite.mapper.AccountLockMapper;
import com.xiaowudr.atmsite.pojo.AccountLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HeartBeatController {
    @Autowired
    private AccountLockMapper accountLockMapper;

    @PostMapping("/heartbeat")
    public void handleFileUpload(
                                 @RequestParam("accountId") String accountId,
                                HttpServletResponse response) {
        //UserAccountService newUserAccountService = new UserAccountService();
        //newUserAccountService.updateantitimestamp(playerID); //7b9e5f745e130536
        // fos.close();
        // Optionally, you can return a success message in the response

        AccountLock accountLock = accountLockMapper.getAccountLock(accountId);
        if(accountLock == null) {
            // initalize account lock
            AccountLock newAccountLock = new AccountLock();
            newAccountLock.setLockType(0);
            newAccountLock.setIsBlocked(0);
            newAccountLock.setAccountId(accountId);
            newAccountLock.setExecPath("initialized");
            accountLockMapper.insertAccountLock(newAccountLock);
        } else {
            // update the value
            accountLockMapper.updateAccountLock(accountLock);
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
