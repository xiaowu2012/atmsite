package com.xiaowudr.atmsite.controller;

import com.xiaowudr.atmsite.pojo.GameUser;
import com.xiaowudr.atmsite.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameUserController {
    @Autowired
    GameUserService gameUserService;

    @GetMapping("/listGameUsers")  // 定义访问URL
    public String  listGameUsers(Model model) {
        // 调用Service获取GameUser列表
        List<GameUser> gameUsers = gameUserService.getTopGameUser();
        // 将gameUsers数据放入Model
        model.addAttribute("gameUsers", gameUsers);
        // 返回展示用户列表的HTML页面
        return "gameuserlist";
    }
}
