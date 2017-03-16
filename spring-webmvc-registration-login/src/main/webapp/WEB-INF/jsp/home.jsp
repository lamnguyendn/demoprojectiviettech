<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <%--Bootstrap CSS--%>
        <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        YOUR HOME PAGE

        <br>
        <a href="/sign-up">Sign up</a>

        <br>

        <c:if test="${message != null}">
            <div class="alert ${cssBootstrap}">
                <c:out value="${message}"/>
            </div>
        </c:if>
    </body>
</html>
