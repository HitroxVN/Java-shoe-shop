<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đăng ký</title>
</head>
<body>
    <form action="register" method="post">
    <h2>Đăng ký tài khoản</h2>
    <c:if test="${not empty loi }">
    <p>${loi }</p>
    </c:if>
        Nhập email: <input type="email" name="email" value="${email }"><br>
        Nhập mật khẩu: <input type="password" name="password"><br>
        Nhập lại mật khẩu <input type="password" name="repassword"><br>
        <button type="submit">Đăng ký</button>
    </form>
    <a href="dangnhap">Đăng nhập ngay</a><br>
    <a href="home">Trở về trang chủ</a>
</body>
</html>