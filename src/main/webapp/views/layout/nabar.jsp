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
	<!-- nabar -->
    <nav class="sidebar">
        <div class="inner-wrap">
            <div class="inner-menu">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/statistic/home">trang chủ thống kê</a></li>
                    <li><a href="${pageContext.request.contextPath}/statistic/product">thống kê chi tiết sản phẩm</a></li>
                    <li><a href="${pageContext.request.contextPath}/statistic/order">thống kê chi tiết đơn hàng</a></li>
                    <li><a href="${pageContext.request.contextPath}/statistic/categories">thống kê chi tiết thể loại</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- end nabar -->
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>

</html>