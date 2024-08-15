<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <button type="submit">Register</button>
            or
            <a href="${pageContext.request.contextPath}/login" class="button">Back to Login page.</a>
        </form>
    </fieldset>
</div>

</body>
</html>
