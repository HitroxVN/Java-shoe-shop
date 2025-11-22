<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm tài khoản</title>
</head>
<body>
	<form action="admin" method="post">
		<input type="hidden" name="action" value="add">
		Họ tên: <input type="text" name="full_name" value="" placeholder="A Đờ Min"><br>
		Email: <input type="email" name="email" value="" placeholder="test@gmail.com"><br>
		Mật khẩu: <input type="password" name="password" value="" placeholder="mật khẩu"><br>
		Số điện thoại; <input type="tel" name="phone" value="" placeholder="0987654321"><br>
		Địa chỉ: <input type="text" name="address" value="" placeholder="Phú diễn - Hà Nội"><br>
		Loại tài khoản: <select name="role">
			<option value="customer">Khách hàng</option>
			<option value="staff">Nhân viên</option>
			<option value="admin">Admin</option>
		</select><br>
		<button type="submit">Thêm tài khoản</button>
	</form>

	<a href="admin">Quay lại trang admin</a>
</body>
</html>