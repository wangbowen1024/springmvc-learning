# SpringMVC学习记录
##目录
1. [springmvc入门](#springmvc入门)
	* 导入坐标
	* 配置核心的控制器（配置DispatcherServlet）
	* 编写springmvc.xml的配置文件
	* 编写Controller控制器类
	* 组件分析
2. 参数绑定
	* 基本数据类型
	* 单个实体类数据类型
	* 实体类中含有实体类
	* 参数是集合
	* 自定义类型转化器
3. [解决请求参数中文乱码问题](#解决请求参数中文乱码问题)


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
	* mthod 指定该方法的请求方式（method = RequestMethod.POST）
	* params 指定限制请求参数的条件（params = {"username","password=123"}）
	* headers 发送的请求中必须包含的请求头（headers = {"Cookie"}）

### 入门案例中的组件分析
* 前端控制器（DispatcherServlet） 
* 处理器映射器（HandlerMapping）
* 处理器（Handler） 
* 处理器适配器（HandlAdapter） 
* 视图解析器（View Resolver） 
* 视图（View）
![](https://github.com/wangbowen1024/springmvc-learning/blob/master/No01_springmvc%E5%85%A5%E9%97%A8/src/main/webapp/images/springmvc%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B%E5%8E%9F%E7%90%86.jpg?raw=true)

## 参数绑定
### 基本数据类型
只要提交表单的name值和请求方法参数名一致，就能自动转换。
```java
@RequestMapping("/param1")
public String paramTest(String username, int age) {
    System.out.println(username + "," + age);
    return "success";
}
```
```html
<form action="param1">
    username:<input name="username" type="text"/><br/>
    age:<input name="age" type="text"/><br/>
    <input type="submit" value="提交"/>
</form>
```

### 单个实体类数据类型
提供一个JavaBean，只要表单中name和实体类中的属性名对应上就可以进行绑定。
```java
public class Account implements Serializable {

    private String account;
    private double money;

	// 省略Get/Set/toString方法...
}
```
```java
@RequestMapping("/param2")
public String paramTest2(Account account) {
    System.out.println(account);
    return "success";
}
```
```html
<form action="param2">
    account:<input name="account" type="text"/><br/>
    money:<input name="money" type="text"/><br/>
    <input type="submit" value="提交"/>
</form>
```

### 实体类中含有实体类
如果实体类中含有实体类，就将被包含的实体类属性名当作一级调用通过.来绑定另一个实体类中的属性。
```java
public class Student implements Serializable {
    private String name;
    private Account account;

    // 省略Get/Set/toString方法...
}
```
```java
@RequestMapping("/param3")
public String paramTest3(Student student) {
    System.out.println(student);
    return "success";
}
```
```html
<form action="param3">
    name:<input name="name" type="text"/><br/>
    account:<input name="account.account" type="text"/><br/>
    money:<input name="account.money" type="text"/><br/>
    <input type="submit" value="提交"/>
</form>
```

### 参数是集合
以下是List和Map的例子，其他集合类似：
```java
public class School implements Serializable {
    private String name;
    private List<Account> list;
    private Map<Integer, Student> map;

    // 省略Get/Set/toString方法...
}
```
```java
@RequestMapping("/param4")
public String paramTest4(School school) {
    System.out.println(school.getName());
    System.out.println(school.getList());
    System.out.println(school.getMap());
    return "success";
}
```
```html
<form action="param4">
    name:<input name="name" type="text"/><br/>
    <%-- List --%>
    account1:<input name="list[0].account" type="text"/><br/>
    money1:<input name="list[0].money" type="text"/><br/>
    account2:<input name="list[1].account" type="text"/><br/>
    money2:<input name="list[1].money" type="text"/><br/>
    <%-- Map --%>
    name:<input name="map['001'].name" type="text"/><br/>
    account:<input name="map['001'].account.account" type="text"/><br/>
    money:<input name="map['001'].account.money" type="text"/><br/>

    <input type="submit" value="提交"/>
</form>
```

### 自定义转换器
1. 编写转换类，实现Converter接口，该接口中的泛型，前面的类是待转换的类型，后面的是转换之后的类型。
```java
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        if (s != null && !"".equals(s)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
```
2. 配置springmvc.xml
```xml
<!-- IOC注册类型转换服务工厂bean -->
<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
    <property name="converters">
        <set>
            <!-- IOC注册类型转换器，并添加到服务中 -->
            <!--<bean id="dateConverter" class="com.springmvc.utils.DateConverter"/>-->
        </set>
    </property>
</bean>
<!-- 开启SpringMVC框架注解的支持,同时开启类型转换服务支持 -->
<mvc:annotation-driven conversion-service="conversionService"/>
```

## 解决请求参数中文乱码问题
在web.xml中添加过滤器
```xml
  <!-- 配置过滤器，解决中文乱码 -->
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```