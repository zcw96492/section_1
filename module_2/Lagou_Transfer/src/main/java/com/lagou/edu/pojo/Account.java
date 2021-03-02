package com.lagou.edu.pojo;

/**
 * 账户表模型
 * @author zhouchaowei
 */
public class Account {

    
    private String accountCardNo;
    private String userName;
    private int balance;

    public String getAccountCardNo() {
        return accountCardNo;
    }

    public void setAccountCardNo(String accountCardNo) {
        this.accountCardNo = accountCardNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountCardNo='" + accountCardNo + '\'' +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
