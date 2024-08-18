<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div>
    <fieldset>
        <h3>Register</h3>
        <form action="${pageContext.request.contextPath}/reg" method="post">
            <label>Login:
                <input name="login" type="text">
            </label><br><br>
            <label>Password:
                <input name="password" type="password">
            </label><br><br>
            <c:forEach var="error" items="${requestScope.errors}">
                <h3 style="color: red">${error}</h3>
            </c:forEach>
            <button type="submit">Register</button>
            or
            <a href="${pageContext.request.contextPath}/login" class="button">Back to Login page.</a>
        </form>
    </fieldset>
</div>

</body>
</html>
