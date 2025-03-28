package com.xiaowudr.atmsite.controller;

import com.xiaowudr.atmsite.pojo.Account;
import com.xiaowudr.atmsite.pojo.AccountTime;
import com.xiaowudr.atmsite.pojo.RegistrationCodes;
import com.xiaowudr.atmsite.service.AccountTimeService;
import com.xiaowudr.atmsite.service.RegistrationCodesService;
import com.xiaowudr.atmsite.service.UserAccountService;
import com.xiaowudr.atmsite.util.TTASP;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserAccountService userService;

    @Autowired
    RegistrationCodesService registrationCodesService;

    @Autowired
    AccountTimeService accountTimeService;

    /**
     * Lock or unlock a user account.
     *
     * @param accountId The user account ID
     * @param lockType  Lock type: 1 to lock, 0 to unlock
     * @return JSON response indicating the operation result
     */
    @PostMapping("/lockuser")
    @ResponseBody
    public ResponseEntity<String> lockUser(
            @RequestParam("accountId") String accountId,
            @RequestParam("lockType") int lockType,
            @RequestParam("execPath") String execPath) {

        userService.lockUser(accountId, lockType, execPath);

        return ResponseEntity.ok("success");
    }


    @GetMapping("/unlock")
    public String unlock(Model model) {
        return "unlockuser";
    }

    @PostMapping("/unlockuser")
    public String unlockUser(
            @RequestParam("adminPassword") String adminPassword,
            @RequestParam("accountId") String accountId,
            Model model) {
        if (!"201219".equals(adminPassword)) {
            model.addAttribute("error", "管理员密码错误");
        } else {
            Account account = userService.getUserByAccount(accountId);
            if(account!=null) {
                userService.unlockUser(accountId);
                model.addAttribute("message", "账号解锁成功");
            } else {
                model.addAttribute("error", "该账号不存在");
            }
        }
        return "unlockuser";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @GetMapping("/changepasswd")
    public String changepasswd(Model model) {
        model.addAttribute("account", new Account());
        return "changepasswd";
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
                    model.addAttribute("account", account);
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

    @PostMapping("/changepasswd")
    public String changePasswords(Account account, Model model) {
        // first to check whether the account exists or not
        Account targetAccount = userService.getUserByAccount(account.getAccountID());
        if (targetAccount == null) {
            model.addAttribute("message", "错误：提供的账号不存在。");
            return "changepasswd";
        } else {
            //
            String currentPassword = targetAccount.getEncryptedPassword();
            String inputEncryptedPassword = TTASP.ttasp_Password(account.getPassword());
            if (inputEncryptedPassword.equals(currentPassword)) {
                String currentTransactionPassword = targetAccount.getTransactionPassword();
                String inputTransactionPassword = account.getTransactionPassword();
                if (inputTransactionPassword.equals(currentTransactionPassword)) {
                    // allow to change password
                    String newPassword = TTASP.ttasp_Password(account.getNewPassword());
                    String newTransactionPassword = account.getNewTransactionPassword();
                    targetAccount.setPassword(account.getNewPassword());// only for success display
                    targetAccount.setEncryptedPassword(newPassword);
                    targetAccount.setTransactionPassword(newTransactionPassword);
                    if (Strings.isNotBlank(account.getEmail())) {
                        targetAccount.setEmail(account.getEmail());
                    }
                    if (Strings.isNotBlank(account.getIdCard())) {
                        targetAccount.setIdCard(account.getIdCard());
                    }
                    if (Strings.isNotBlank(account.getName())) {
                        targetAccount.setName(account.getName());
                    }
                    userService.updateUser(targetAccount);
                    model.addAttribute("account", targetAccount);
                    return "changepwsuccess";
                } else {
                    model.addAttribute("message", "错误：提供的原交易密码错误。");
                    return "changepasswd";
                }

            } else {
                model.addAttribute("message", "错误：提供的原登录密码错误。");
                return "changepasswd";
            }
        }
    }
}
