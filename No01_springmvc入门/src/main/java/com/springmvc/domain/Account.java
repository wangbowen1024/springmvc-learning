package com.springmvc.domain;

import java.io.Serializable;

/**
 * Account class
 *
 * @author BowenWang
 * @date 2019/07/30
 */
public class Account implements Serializable {

    private String account;
    private double money;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", money=" + money +
                '}';
    }
}
