package com.xiaowudr.atmsite.controller;

import com.xiaowudr.atmsite.pojo.AntiScriptAccount;
import com.xiaowudr.atmsite.pojo.GameUser;
import com.xiaowudr.atmsite.service.AntiScriptAccountService;
import com.xiaowudr.atmsite.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AntiScriptAccountController {
    @Autowired
    AntiScriptAccountService antiScriptAccountService;

    @GetMapping("/getantiscriptaccounts")  // 定义访问URL
    public String  listGameUsers(Model model) {
        // 调用Service获取GameUser列表
        List<AntiScriptAccount> antiScriptAccounts = antiScriptAccountService.getAntiScriptAccountList();
        // 将gameUsers数据放入Model
        model.addAttribute("antiscriptaccounts", antiScriptAccounts);
        // 返回展示用户列表的HTML页面
        return "antiscriptaccountlist";
    }
}
