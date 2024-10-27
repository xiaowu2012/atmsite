package com.xiaowudr.atmsite.pojo;

import java.util.Date;

public class AccountLock {

    private String accountId;
    private int lockType;
    private int isBlocked;

    public String getExecPath() {
        return execPath;
    }

    public void setExecPath(String execPath) {
        this.execPath = execPath;
    }

    private String execPath;

  /*  public Date getAntiTimeStramp() {
        return antiTimeStramp;
    }

    public void setAntiTimeStramp(Date antiTimeStramp) {
        this.antiTimeStramp = antiTimeStramp;
    }*/

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getLockType() {
        return lockType;
    }

    public void setLockType(int lockType) {
        this.lockType = lockType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

   // private Date antiTimeStramp;
}
