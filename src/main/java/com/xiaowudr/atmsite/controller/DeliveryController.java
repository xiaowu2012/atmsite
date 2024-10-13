package com.xiaowudr.atmsite.controller;
import com.xiaowudr.atmsite.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/deliver")  // 显示充值页面
    public String showRechargePage() {
        return "deliver_item";  // 返回Thymeleaf模板的名称
    }

    @PostMapping("/deliveritem")
    public String deliverItem(
            @RequestParam("adminPassword") String adminPassword,
            @RequestParam("gameAccount") String gameAccount,
            @RequestParam(value = "moonBoxItemId", required = false) Integer moonBoxItemId,
            @RequestParam(value = "moonCakeItemId", required = false) Integer moonCakeItemId,
            @RequestParam(value = "moonBoxCount", required = false) Integer moonBoxCount,
            @RequestParam(value = "moonCakeCount", required = false) Integer moonCakeCount,
            Model model) {

        // 验证管理员密码
        if (!"201219".equals(adminPassword)) {
            model.addAttribute("error", "管理员密码错误");
            return "deliver_item";
        }


        // 调用服务层处理发货逻辑
        boolean success_moonBox = deliveryService.deliverItem(gameAccount, moonBoxItemId, moonBoxCount);
        boolean success_moonCake = deliveryService.deliverItem(gameAccount, moonCakeItemId, moonCakeCount);

        if (success_moonBox && success_moonCake ) {
            model.addAttribute("message", "发货成功！");
        } else {
            model.addAttribute("error", "发货失败，请检查输入信息");
        }

        model.addAttribute("adminPassword", adminPassword);

        return "deliver_item";
    }
}
