# SpringMVC学习记录
##目录
1. [springmvc入门](#springmvc入门)
	* 导入坐标
	* 配置核心的控制器（配置DispatcherServlet）
	* 编写springmvc.xml的配置文件
	* 编写Controller控制器类
	* 组件分析


## springmvc入门 
### 导入坐标
```xml
<properties>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<!-- JDK1.8 -->
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>
<!-- 版本锁定 -->
<spring.version>5.0.2.RELEASE</spring.version>
</properties>

<dependencies>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>${spring.version}</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-web</artifactId>
  <version>${spring.version}</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>${spring.version}</version>
</dependency>

<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>servlet-api</artifactId>
  <version>2.5</version>
  <scope>provided</scope>
</dependency>

<dependency>
  <groupId>javax.servlet.jsp</groupId>
  <artifactId>jsp-api</artifactId>
  <version>2.0</version>
  <scope>provided</scope>
</dependency>
</dependencies>
```

###  配置核心的控制器（配置DispatcherServlet）
* 在web.xml配置文件中核心控制器DispatcherServlet
```xml
  <!-- SpringMVC的核心控制器 -->
  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 配置Servlet的初始化参数，读取springmvc的配置文件，创建spring容器 -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <!-- 在项目启动的时候加载 -->
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```

### 编写springmvc.xml的配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 开启扫描注解 -->
    <context:component-scan base-package="com.springmvc"/>

    <!-- IOC创建视图解析器 -->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 设置前后缀 -->
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!-- 开启SpringMVC框架注解的支持 -->
    <mvc:annotation-driven/>
</beans>
```

### 编写Controller控制器类
```java
/**
 * HelloController class
 * 控制器类
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
```
RequestMapping注解
1. RequestMapping注解的作用是建立请求URL和处理方法之间的对应关系
2. RequestMapping注解可以作用在方法和类上
	* 作用在类上：第一级的访问目录
	* 作用在方法上：第二级的访问目录
	* 细节：路径可以不编写 / 表示应用的根目录开始
	* 细节：${ pageContext.request.contextPath }也可以省略不写，但是路径上不能写 /
3. RequestMapping的属性
	* path 指定请求路径的url
	* value value属性和path属性是一样的
	* mthod 指定该方法的请求方式
	* params 指定限制请求参数的条件
	* headers 发送的请求中必须包含的请求头

### 入门案例中的组件分析
* 前端控制器（DispatcherServlet） 
* 处理器映射器（HandlerMapping）
* 处理器（Handler） 
* 处理器适配器（HandlAdapter） 
* 视图解析器（View Resolver） 
* 视图（View）