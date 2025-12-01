<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa thông tin</title>
</head>
<body>
	<form action="users" method="post">
	<h2>Sửa thông tin cá nhân</h2>
	<c:if test="${not empty thanhcong }">${thanhcong }</c:if>
	<c:if test="${not empty loicapnhap }">${loicapnhap }</c:if>
		<input type="hidden" name="user_id">
		<br>
		Họ tên: <input type="text" name="full_name" value="${user.full_name }" placeholder="họ tên">
		<c:if test="${not empty loi.loiTen }">${loi.loiTen }</c:if><br>
		
		Email: <input type="email" name="email" value="${user.email }" placeholder="test@gmail.com">
		<c:if test="${not empty loi.loiEmail }">${loi.loiEmail }</c:if>
		<c:if test="${not empty loiEmail }">${loiEmail }</c:if><br>
		
		Số điện thoại; <input type="tel" name="phone" value="${user.phone }" placeholder="0987654321">
		<c:if test="${not empty loi.loiPhone }">${loi.loiPhone }</c:if><br>
		
		Địa chỉ: <input type="text" name="address" value="${user.address }" placeholder="Phú diễn - Hà Nội">
		<c:if test="${not empty loi.loiAddress }">${loi.loiAddress }</c:if><br>
		
		<button type="submit">Cập nhập</button>
	</form>
	<a href="logout"> Đăng xuất</a><br>
	<a href="home">Trở về trang chủ</a>
</body>
</html>