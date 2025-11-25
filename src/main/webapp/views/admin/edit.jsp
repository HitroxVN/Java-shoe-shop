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
		<input type="hidden" name="user_id" value="${inputEdit.id}">
		Họ tên: <input type="text" name="full_name" value="${inputEdit.full_name }" placeholder="A Đờ Min">
		<c:if test="${not empty loi.loiTen }">${loi.loiTen }</c:if><br>
		
		Email: <input type="email" name="email" value="${inputEdit.email }" placeholder="test@gmail.com">
		<c:if test="${not empty loi.loiEmail }">${loi.loiEmail }</c:if><br>
		
		Số điện thoại; <input type="tel" name="phone" value="${inputEdit.phone }" placeholder="0987654321">
		<c:if test="${not empty loi.loiPhone }">${loi.loiPhone }</c:if><br>
		
		Địa chỉ: <input type="text" name="address" value="${inputEdit.address }" placeholder="Phú diễn - Hà Nội">
		<c:if test="${not empty loi.loiAddress }">${loi.loiAddress }</c:if><br>
		
        Loại tài khoản: <select name="role">
			<option value="customer" <c:if test="${inputEdit.role == 'customer'}">selected</c:if> >Khách hàng</option>
			<option value="staff" <c:if test="${inputEdit.role == 'staff'}">selected</c:if>>Nhân viên</option>
			<option value="admin" <c:if test="${inputEdit.role == 'admin'}">selected</c:if>>Quản lý</option>
		</select><br>
		<button type="submit">Cập nhập</button>
	</form>

	<a href="admin">Quay lại trang admin</a>
</body>
</html>