<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <div class="product">
            <div class="container-fluid">
                <div class="title">đây trang thống kê đơn hàng</div>
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-12">
                        <div class="desc">tổng số đơn hàng :</div>
                        <div class="statistic statistic-one">
                            <p class="content">${tongSoDonHang}</p>
                        </div>
                    </div>
                </div>
                <hr class="mt-4">
                <h3 class="mt-4">thống kê trạng thái đơn hàng</h3>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Số lượng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${status}">
                        	<tr>
                        		<td>${m.key}</td>
                        		<td>${m.value}</td>
                        	</tr>
                        </c:forEach>
                    </tbody>
                </table>
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