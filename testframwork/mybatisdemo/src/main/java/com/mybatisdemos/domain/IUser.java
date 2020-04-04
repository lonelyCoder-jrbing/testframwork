package com.mybatisdemos.domain;

public class IUser {
    private String userName;
    private String password;
    private String patternLock;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPatternLock() {
        return patternLock;
    }

    public void setPatternLock(String patternLock) {
        this.patternLock = patternLock;
    }
}
