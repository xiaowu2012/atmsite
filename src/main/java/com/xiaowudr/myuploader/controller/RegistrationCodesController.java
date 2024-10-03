package com.xiaowudr.myuploader.controller;

import com.xiaowudr.myuploader.pojo.RegistrationCodes;
import com.xiaowudr.myuploader.service.RegistrationCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;

@Controller
public class RegistrationCodesController {
    @Autowired
    RegistrationCodesService registrationCodesService;


    @GetMapping("/generate")  // 定义访问URL
    public String showGenerateCodePage() {
        return "generate_code";  // 返回Thymeleaf模板的名称
    }

    @PostMapping("/generateCode")
    public String generateCode(@RequestParam("adminPassword") String adminPassword,
                               @RequestParam("codeType") String codeType,
                               Model model) {
        // 验证管理员密码（此处为示例，实际可以从数据库或配置中获取正确的密码）
        String correctPassword = "admin123";  // 请替换为实际的管理员密码
        if (!adminPassword.equals(correctPassword)) {
            model.addAttribute("error", "管理员密码错误");
            return "generate_code";
        }

        // 根据类型生成注册码
        String registrationCode = generateRandomCode();  // 使用UUID作为示例注册码
        RegistrationCodes registrationCodes = new RegistrationCodes();
        registrationCodes.setIsUsed(0);
        registrationCodes.setRegisterNo(registrationCode);
        if ("year".equals(codeType)) {
            registrationCodes.setRegisterType(1);
        } else {
            registrationCodes.setRegisterType(0);

        }
        registrationCodesService.insertRegistrationCodes(registrationCodes);

        // 将生成的注册码和选定的类型传递给视图
        model.addAttribute("registrationCode", registrationCode);
        model.addAttribute("codeType", codeType);
        model.addAttribute("adminPass", adminPassword);
        return "generate_code";  // 返回到同一页面，并显示结果
    }

    // 定义字母和数字的组合
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;  // 指定生成码的长度

    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        // 随机从字符集中选择字符并组成10位字符串
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }

        return code.toString();
    }
}
