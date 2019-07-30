package com.springmvc.domain;

import java.io.Serializable;

/**
 * Student class
 *
 * @author BowenWang
 * @date 2019/07/30
 */
public class Student implements Serializable {
    private String name;
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}
