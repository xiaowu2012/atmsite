package com.xiaowudr.atmsite.pojo;

public class GameUser {

    private String userID;
    private int level;
    private int byClass;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getByClass() {
        return byClass;
    }

    public void setByClass(int byClass) {
        this.byClass = byClass;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    private int gender;
}
