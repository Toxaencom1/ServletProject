<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Advertisement</title>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
</head>
<body>
<div id="main">
    <form action="${pageContext.request.contextPath}/ad" method="post">
        <h3>Create Advertisement</h3>
        <label for="name">Name: </label>
        <input id="name" name="name" type="text"><br><br>
        <label for="model">Model: </label>
        <input id="model" name="model" type="text"><br><br>
        <label for="cost">Cost: </label>
        <input id="cost" name="cost" type="number" min="0"><br><br>
        <label for="url">Img: </label>
        <input id="url" name="url" type="text"><br><br>
        <button type="submit">Save</button>
    </form>
</div>
<div class="list">
    <c:forEach var="element" items="${requestScope.elements}">
        <div class="element">
            <p id="name-${element.id}">Name: ${element.name}</p>
            <p id="model-${element.id}">Model: ${element.model}</p>
            <p id="cost-${element.id}">Cost: ${element.cost} </p>
            <form action="/ad/delete/${element.id}" method="post"
                  onsubmit="return confirm('Are you sure you want to delete this item?');">
                <button id="delete-${element.id}" type="submit">Delete Ad</button>
            </form>
            <img id="url-${element.id}" class="images" src="${element.url}" alt="Wrong URL of car photo">
        </div>
    </c:forEach>
    <div id="no-ads-message">No advertisements</div>
</div>
</body>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var elements = document.querySelectorAll('.list .element');
        var noAdsMessage = document.getElementById('no-ads-message');

        if (elements.length === 0) {
            noAdsMessage.style.display = 'block';
        } else {
            noAdsMessage.style.display = 'none';
        }
    });
</script>
</html>