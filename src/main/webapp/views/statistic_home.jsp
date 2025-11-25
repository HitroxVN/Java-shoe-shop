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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/views/css/style.css">
</head>

<body>
	<jsp:include page="/views/layout/header.jsp"></jsp:include>
	<jsp:include page="/views/layout/nabar.jsp"></jsp:include>
	<!-- main -->
	<main class="main">
		<div class="container-fluid">
			<div class="title">đây trang chủ thống kê !</div>
			<div class="row">
				<div class="col-lg-3 col-md-6 col-12">
					<div class="desc">tổng doanh thu :</div>
					<div class="statistic statistic-one">
						<p class="content">${doanhThu}</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<div class="desc">tổng số đơn hàng :</div>
					<div class="statistic statistic-two">
						<p class="content">${tongDonHang}</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<div class="desc">tổng số mặt hàng :</div>
					<div class="statistic statistic-three">
						<p class="content">${tongSoMatHang}</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-12">
					<div class="desc">tổng số tài khoản :</div>
					<div class="statistic statistic-four">
						<p class="content">${tongSoTaiKhoan}</p>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!--end main -->



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>

</html>