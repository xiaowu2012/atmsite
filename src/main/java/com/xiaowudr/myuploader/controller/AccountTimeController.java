package com.xiaowudr.myuploader.controller;

import com.xiaowudr.myuploader.service.AccountTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountTimeController {

    @Autowired
    private AccountTimeService accountTimeService;

    @GetMapping("/recharge")  // 显示充值页面
    public String showRechargePage() {
        return "recharge_time";  // 返回Thymeleaf模板的名称
    }

    @PostMapping("/rechargeTime")
    public String rechargeAccountTime(@RequestParam("adminPassword") String adminPassword,
                                      @RequestParam("account") String account,
                                      @RequestParam("rechargeType") String rechargeType,
                                      Model model) {
        // 验证管理员密码（此处为示例，实际可以从数据库或配置中获取正确的密码）
        String correctPassword = "admin123";  // 请替换为实际的管理员密码
        if (!adminPassword.equals(correctPassword)) {
            model.addAttribute("error", "管理员密码错误");
            return "recharge_time";
        }

        // 设置充值天数，根据充值类型决定是年卡还是月卡
        int rechargeMonths = 0;
        if ("year".equals(rechargeType)) {
            rechargeMonths = 12;
        } else if ("month".equals(rechargeType)) {
            rechargeMonths = 1;
        }

        // 插入数据到数据库
        accountTimeService.addUserAccountTime(account, rechargeMonths);

        // 传递数据到页面，确认充值成功
        model.addAttribute("account", account);
        model.addAttribute("rechargeDays", rechargeMonths);
        model.addAttribute("rechargeType", rechargeType);
        model.addAttribute("adminPassword", adminPassword);

        return "recharge_time";  // 返回到同一页面，并显示结果
    }
}
