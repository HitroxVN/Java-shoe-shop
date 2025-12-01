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
	
	<label>Tìm kiếm tài khoản theo tên, email, sđt</label>
	<form action="${pageContext.request.contextPath}/admin/users" method="get">
	<input type="hidden" name="action" value="search">
	<input type="text" name="keyword" placeholder="Nhập tên cần tìm kiếm">
	<button type="submit">Tìm kiếm</button>
	</form>

<label>Lọc theo role</label>
	<form action="${pageContext.request.contextPath}/admin/users" method="get">
		<input type="hidden" name="action" value="role_filter"> <select name="role">
			<option value="customer"
				${param.role == 'customer' ? 'selected' : ''}>Khách hàng</option>
			<option value="admin" ${param.role == 'admin' ? 'selected' : ''}>Quản
				lý</option>
		</select>

		<button type="submit">Lọc</button>
	</form>
	
	<a href="${pageContext.request.contextPath}/admin/users">Hiển thị tất cả</a><br>
	<a href="${pageContext.request.contextPath}/admin/users?action=add">Thêm tài khoản</a>
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
					<th>
					<c:choose>
						<c:when test="${u.role == 'customer'}">Khách hàng</c:when>
						<c:when test="${u.role == 'admin'}">Quản lý</c:when>
					</c:choose>
					</th>
					<th><a href="${pageContext.request.contextPath}/admin/users?action=edit&user_id=${u.id }">Sửa</a>
					<form action="${pageContext.request.contextPath}/admin/users" method="post" onsubmit="return confirm('Xác nhận xoá user_id: ${u.id}');">
						<input type="hidden" name="action" value="delete">
						<input type="hidden" name="user_id" value="${u.id}">
						<button type="submit">Xoá</button>
					</form>
					</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="../admin">Trang quản lý</a>
</body>
</html>