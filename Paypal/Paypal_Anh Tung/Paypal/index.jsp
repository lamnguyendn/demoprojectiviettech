<%--
  Created by IntelliJ IDEA.
  User: TungNguyen
  Date: 8/12/16
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${initParam['posturl']}" method="post">
    <!-- Saved buttons use the "secure click" command -->
    <input type="hidden" name="cmd" value="${initParam['cmd']}"/>
    <br>
    <input  type="hidden" name="business" value="${initParam['business']}"/><br>
    <input  type="hidden" name="tx" value="1234"/><br> <%--trancsation ID--%>

    <input type="hidden" name="password" value="${initParam['password']}"/><br>
    <input type="hidden" name="cert_id" value="${initParam['signature']}" /><br>
    <!-- Saved buttons are identified by their button IDs -->
   Gia <input type="" name="amount" value=""/><br>
    <!-- Saved buttons display an appropriate button image. -->
    <input type="hidden" name="cancel_return" value="${initParam['cancelurl']}" /><br>
    <input type="hidden" name="return" value="${initParam['returnurl']}" /><br>
    <input type="submit" name="Donate" value="Thanh toan" /><br>
</form>
</body>
</html>
