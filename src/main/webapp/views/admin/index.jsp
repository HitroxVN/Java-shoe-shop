<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang admin</title>
</head>
<body>
	<h2>Danh sách tài khoản người dùng</h2>
	<a href="admin?action=add">Thêm tài khoản</a>
	<c:if test="${not empty thanhcong }">
		<p>${thanhcong }</p>
	</c:if>
	<c:if test="${not empty loi }">
		<p>${loi }</p>
	</c:if>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Họ tên</th>
				<th>Email</th>
				<th>Số điện thoại</th>
				<th>Địa chỉ</th>
				<th>Loại tài khoản</th>
				<th>Sửa</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="u" items="${users}">
				<tr>
					<td>${u.id}</td>
					<th>${u.full_name }</th>
					<th>${u.email }</th>
					<th>${u.phone }</th>
					<th>${u.address }</th>
					<th>${u.role }</th>
					<th><a href="admin?action=edit&user_id=${u.id }">Sửa</a>
					<form action="admin" method="post" onsubmit="return confirm('Xác nhận xoá user_id: ${u.id}');">
						<input type="hidden" name="action" value="delete">
						<input type="hidden" name="user_id" value="${u.id}">
						<button type="submit">Xoá</button>
					</form>
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>