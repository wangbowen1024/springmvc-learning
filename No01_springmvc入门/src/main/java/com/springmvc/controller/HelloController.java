package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloController class
 * 欢迎控制器类
 *
 * @author BowenWang
 * @date 2019/07/29
 */
@Controller // 添加到spring容器
public class HelloController {

    /**
     *  接受请求
     *  @RequestMapping(path = "/hello")
     *  @RequestMapping(value = "/hello")
     *  @RequestMapping("/hello")
     *  上面三种等价
     */
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("First SpringMVC...");
        // 跳转页面名称，在配置文件springmvc.xml中已经写好前后缀，所以这直接省略
        return "success";
    }
}
