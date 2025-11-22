<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa thông tin tài khoản</title>
</head>
<body>
    <form action="admin" method="post">
		<input type="hidden" name="action" value="edit">
		<input type="hidden" name="user_id" value="${user.id }">
		Họ tên: <input type="text" name="full_name" value="${user.full_name }" placeholder="A Đờ Min"><br>
		Email: <input type="email" name="email" value="${user.email }" placeholder="test@gmail.com"><br>
		Số điện thoại; <input type="tel" name="phone" value="${user.phone }" placeholder="0987654321"><br>
		Địa chỉ: <input type="text" name="address" value="${user.address }" placeholder="Phú diễn - Hà Nội"><br>
        Loại tài khoản: <select name="role">
			<option value="customer" <c:if test="${user.role == 'customer'}">selected</c:if> >Khách hàng</option>
			<option value="staff" <c:if test="${user.role == 'staff'}">selected</c:if>>Nhân viên</option>
			<option value="admin" <c:if test="${user.role == 'admin'}">selected</c:if>>Quản lý</option>
		</select><br>
		<button type="submit">Cập nhập</button>
	</form>

	<a href="admin">Quay lại trang admin</a>
</body>
</html>