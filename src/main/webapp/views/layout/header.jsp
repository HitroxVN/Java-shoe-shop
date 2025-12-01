<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/views/css/style.css">
</head>

<body>
	<!-- header -->
	<header class="header">
		<div class="inner-wrap">
			<div class="inner-logo">
				<a href="#">Shop 4T</a>
			</div>
			<div class="inner-list">
				<ul>
					<li><a href="../ProductController">sản phẩm</a></li>
					<li><a href="../admin/users">tài khoản</a></li>
					<li><a href="../OrderController">đơn hàng</a></li>
				</ul>
			</div>
			<div class="inner-auth">
				<a href="../admin">Trang quản lý</a>
			</div>
		</div>
	</header>
	<!--end  header -->
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>

</html>