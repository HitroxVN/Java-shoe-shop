<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đơn hàng ${orderId}</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #000;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h2>
		Chi tiết đơn hàng
		<c:out value="${orderId}" />
	</h2>

	<c:choose>
		<c:when test="${not empty items}">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>tên sản phẩm</th>
						<th>Số lượng</th>
						<th>Giá</th>
						<th>Ngày tạo</th>
						<th>Ngày cập nhật</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${items}">
						<tr>
							<td>${item.id}</td>
							<td><c:forEach var="ip" items="${pp}">
									<c:if test="${ip.id == item.product_id}">
							   ${ip.name}
							</c:if>
								</c:forEach></td>
							<td>${item.quantity}</td>
							<td>${item.price}</td>
							<td>${item.created_at}</td>
							<td>${item.updated_at}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>Đơn hàng này chưa có sản phẩm nào.</p>
		</c:otherwise>
	</c:choose>

	<br>
	<a href="UserOrderController?action=orderList">Quay lại danh sách
		đơn hàng</a>

</body>
</html>
