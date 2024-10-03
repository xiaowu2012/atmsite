package com.xiaowudr.myuploader.controller;

import com.xiaowudr.myuploader.pojo.AccountTime;
import com.xiaowudr.myuploader.pojo.RegistrationCodes;
import com.xiaowudr.myuploader.pojo.User;
import com.xiaowudr.myuploader.service.AccountTimeService;
import com.xiaowudr.myuploader.service.RegistrationCodesService;
import com.xiaowudr.myuploader.service.UserAccountService;
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
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        User targetUser = userService.getUserByAccount(user.getAccount());


        // it is not used, and target user exists
        if (targetUser == null) {
            RegistrationCodes registrationCodes = registrationCodesService.getRegistrationCodes(user.getRegisterCode());
            if (registrationCodes != null) {
                boolean isGoodToUse = registrationCodes.getIsUsed() == 0;
                if (isGoodToUse) {
                    userService.registerUser(user);
                    if (registrationCodes.getRegisterType() == 1) {
                        AccountTime accountTime = new AccountTime();
                        accountTime.setAccount(user.getAccount());
                        accountTime.setDays(30);
                        accountTime.setAccountType(1);
                        accountTime.setTimeRemain(120);
                        accountTimeService.insertAccountTime(accountTime);
                    } else {
                        AccountTime accountTime = new AccountTime();
                        accountTime.setAccount(user.getAccount());
                        accountTime.setDays(1);
                        accountTime.setAccountType(0);
                        accountTime.setTimeRemain(21600);
                        accountTimeService.insertAccountTime(accountTime);
                    }
                    registrationCodes.setIsUsed(1);
                    registrationCodesService.updateIsUsed(registrationCodes);
                    model.addAttribute("user", user);
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
