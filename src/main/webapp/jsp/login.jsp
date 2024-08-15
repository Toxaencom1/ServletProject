<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div>
    <fieldset>
        <h3>Login</h3>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label>Login:
                <input name="login" type="text">
            </label><br><br>
            <label>Password:
                <input name="password" type="password">
            </label><br><br>
            <button type="submit">Log in</button>
            or
            <a href="${pageContext.request.contextPath}/reg" class="button">Register</a>
        </form>
    </fieldset>
</div>

</body>
</html>
