<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang nhân viên</title>
</head>
<body>
	<h2>Danh sách khách hàng</h2>

	<a href="staff">HIển thị tất cả khách hàng</a>
	<br>

	<label>Tìm kiếm tài khoản theo tên, email, sđt</label>
	<form action="staff" method="get">
		<input type="hidden" name="action" value="search"> <input
			type="text" name="keyword" placeholder="Nhập tên cần tìm kiếm">
		<button type="submit">Tìm kiếm</button>
	</form>

	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Họ tên</th>
				<th>Email</th>
				<th>Số điện thoại</th>
				<th>Địa chỉ</th>
				<th>Xem</th>
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
					<th><a href="#">Xem danh sách đơn hàng</a></th>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</body>
</html>