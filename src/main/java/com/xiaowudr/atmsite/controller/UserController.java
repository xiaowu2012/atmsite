package com.xiaowudr.atmsite.controller;

import com.xiaowudr.atmsite.pojo.Account;
import com.xiaowudr.atmsite.pojo.AccountTime;
import com.xiaowudr.atmsite.pojo.RegistrationCodes;
import com.xiaowudr.atmsite.service.AccountTimeService;
import com.xiaowudr.atmsite.service.RegistrationCodesService;
import com.xiaowudr.atmsite.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserAccountService userService;

    @Autowired
    RegistrationCodesService registrationCodesService;

    @Autowired
    AccountTimeService accountTimeService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Account account, Model model) {
        Account targetAccount = userService.getUserByAccount(account.getAccountID());


        // it is not used, and target user exists
        if (targetAccount == null) {
            RegistrationCodes registrationCodes = registrationCodesService.getRegistrationCodes(account.getRegisterCode());
            if (registrationCodes != null) {
                boolean isGoodToUse = registrationCodes.getIsUsed() == 0;
                if (isGoodToUse) {
                    userService.registerUser(account);
                    if (registrationCodes.getRegisterType() == 1) {
                        AccountTime accountTime = new AccountTime();
                        accountTime.setAccount(account.getAccountID());
                        accountTime.setDays(30);
                        accountTime.setAccountType(1);
                        accountTime.setTimeRemain(120);
                        accountTimeService.insertAccountTime(accountTime);
                    } else {
                        AccountTime accountTime = new AccountTime();
                        accountTime.setAccount(account.getAccountID());
                        accountTime.setDays(1);
                        accountTime.setAccountType(0);
                        accountTime.setTimeRemain(21600);
                        accountTimeService.insertAccountTime(accountTime);
                    }
                    registrationCodes.setIsUsed(1);
                    registrationCodesService.updateIsUsed(registrationCodes);
                    model.addAttribute("user", account);
                    return "success";
                } else {
                    model.addAttribute("message", "错误：注册码已经使用。");
                    return "register";
                }
            } else {
                model.addAttribute("message", "错误：无效的注册码。");
                return "register";
            }
        } else {
            model.addAttribute("message", "错误：账号已经注册。");
            return "register";
        }
    }
}
