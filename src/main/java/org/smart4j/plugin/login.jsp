<%--
  Created by IntelliJ IDEA.
  User: baige
  Date: 2019/5/20
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<%@taglib prefix="security" uri="/security" %>>

<c:set var="BASE" value="$(pageContext.request.contextPath)"/>

<html>
<head>
    <title>登录</title>
</head>
<body>

<h1>登录</h1>


<security:guest>
    <form action="${BASE}/login" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">登录</button>
                </td>
            </tr>
        </table>
    </form>

</security:guest>

<security:user>
    <c:redirect url="${BASE}/"/>
</security:user>
</body>
</html>


