package com.springmvc.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * School class
 *
 * @author BowenWang
 * @date 2019/07/30
 */
public class School implements Serializable {
    private String name;
    private List<Account> list;
    private Map<Integer, Student> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }

    public Map<Integer, Student> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Student> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
