<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đăng nhập</title>
</head>
<body>
<form action="login" method="post">
    <h2>Đăng nhập</h2>
    <c:if test="${not empty thanhcong }">
    <p>${thanhcong }</p>
    </c:if>
    <c:if test="${not empty loi }">
    <p>${loi }</p>
    </c:if>
        Nhập email: <input type="email" name="email" value="${email }"><br>
        Nhập mật khẩu: <input type="password" name="password"><br>
        <button type="submit">Đăng nhập</button>
    </form>

</body>
</html>