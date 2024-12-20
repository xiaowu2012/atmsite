package com.xiaowudr.atmsite.pojo;

public class Account {

    private String accountID;               // 游戏帐号
    private String password;              // 游戏密码
    private String newPassword;
    private String encryptedPassword;
    private String transactionPassword;   // 交易密码
    private String newTransactionPassword;
    private String email;                 // 邮箱地址
    private String name;                  // 玩家姓名
    private String idCard;                // 身份证号码
    private String registerCode;          // 注册码

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    // Getter and Setter methods

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String account) {
        this.accountID = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getNewTransactionPassword() {
        return newTransactionPassword;
    }

    public void setNewTransactionPassword(String newTransactionPassword) {
        this.newTransactionPassword = newTransactionPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
