package com.xiaowudr.atmsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")  // 显示充值页面
    public String showRechargePage() {
        return "admin";  // 返回Thymeleaf模板的名称
    }
}
