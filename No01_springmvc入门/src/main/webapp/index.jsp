<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <h1>欢迎页面</h1>
        <a href="hello">跳转成功页面</a>
    </div>

    <div>
        <h1>参数绑定1：基本数据类型</h1>
        <form action="param1">
            username:<input name="username" type="text"/><br/>
            age:<input name="age" type="text"/><br/>
            <input type="submit" value="提交"/>
        </form>
    </div>

    <div>
        <h1>参数绑定2：实体类数据类型</h1>
        <form action="param2">
            account:<input name="account" type="text"/><br/>
            money:<input name="money" type="text"/><br/>
            <input type="submit" value="提交"/>
        </form>
    </div>

    <div>
        <h1>参数绑定3：实体类中包含实体类</h1>
        <form action="param3">
            name:<input name="name" type="text"/><br/>
            account:<input name="account.account" type="text"/><br/>
            money:<input name="account.money" type="text"/><br/>
            <input type="submit" value="提交"/>
        </form>
    </div>

    <div>
        <h1>参数绑定4：参数是集合类型</h1>
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
    </div>

    <div>
        <h1>自定义类型转换器</h1>
        <form action="param5">
            name:<input name="name" type="text"/><br/>
            birthday:<input name="birthday" type="text"/><br/>
            <input type="submit" value="提交"/>
        </form>
    </div>


</body>
</html>
