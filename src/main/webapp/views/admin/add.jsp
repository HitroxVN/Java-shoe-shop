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
		Họ tên: <input type="text" name="full_name" value="${inputAdd.full_name }" placeholder="A Đờ Min">
		<c:if test="${not empty loi.loiTen }">${loi.loiTen }</c:if><br>
		
		Email: <input type="email" name="email" value="${inputAdd.email }" placeholder="test@gmail.com">
		<c:if test="${not empty loi.loiEmail }">${loi.loiEmail }</c:if><br>
		
		Mật khẩu: <input type="password" name="password" value="${inputAdd.password }" placeholder="mật khẩu">
		<c:if test="${not empty loi.loiPassword }">${loi.loiPassword }</c:if><br>
		
		Số điện thoại; <input type="tel" name="phone" value="${inputAdd.phone }" placeholder="0987654321">
		<c:if test="${not empty loi.loiPhone }">${loi.loiPhone }</c:if><br>
		
		Địa chỉ: <input type="text" name="address" value="${inputAdd.address }" placeholder="Phú diễn - Hà Nội">
		<c:if test="${not empty loi.loiAddress }">${loi.loiAddress }</c:if><br>
		
		Loại tài khoản: <select name="role">
			<option value="customer" <c:if test="${inputAdd.role == 'customer' }">selected</c:if>>Khách hàng</option>
			<option value="staff" <c:if test="${inputAdd.role == 'staff' }">selected</c:if>>Nhân viên</option>
			<option value="admin" <c:if test="${inputAdd.role == 'admin' }">selected</c:if>>Admin</option>
		</select>
		<c:if test="${not empty loi.loiRole }">${loi.loiRole }</c:if><br>
		
		<button type="submit">Thêm tài khoản</button>
	</form>

	<a href="admin">Quay lại trang admin</a>
</body>
</html>