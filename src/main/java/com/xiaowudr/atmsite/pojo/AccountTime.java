package com.xiaowudr.atmsite.pojo;

public class AccountTime {
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    String account;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    int days;

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getTimeRemain() {
        return timeRemain;
    }

    public void setTimeRemain(int timeRemain) {
        this.timeRemain = timeRemain;
    }

    int accountType;

    int timeRemain;
}
