package com.springmvc.controller;

import com.springmvc.domain.Account;
import com.springmvc.domain.Person;
import com.springmvc.domain.School;
import com.springmvc.domain.Student;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * ParamController1 class
 * 参数绑定控制器1：绑定基本数据类型
 *
 * @author BowenWang
 * @date 2019/07/30
 */
@Controller
public class ParamController {
    /**
     * 基本数据类型
     * @param username
     * @param age
     * @return
     */
    @RequestMapping("/param1")
    public String paramTest1(String username, int age) {
        System.out.println(username + "," + age);
        return "success";
    }

    /**
     * 实体类数据类型
     * @param account
     * @return
     */
    @RequestMapping("/param2")
    public String paramTest2(Account account) {
        System.out.println(account);
        return "success";
    }

    /**
     * 实体类包含实体类
     * @param student
     * @return
     */
    @RequestMapping("/param3")
    public String paramTest3(Student student) {
        System.out.println(student);
        return "success";
    }

    /**
     * 参数包含集合类型
     * @param school
     * @return
     */
    @RequestMapping("/param4")
    public String paramTest4(School school) {
        System.out.println(school.getName());
        System.out.println(school.getList());
        System.out.println(school.getMap());
        return "success";
    }


    /**
     * 自定义类型转换器
     * @param person
     * @return
     */
    @RequestMapping("/param5")
    public String paramTest5(Person person) {
        System.out.println(person);
        return "success";
    }

}
