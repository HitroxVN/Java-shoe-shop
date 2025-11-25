<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body { font-family: Arial; margin: 0; background: #f0f0f0; }
    .header { background: #c00; padding: 15px; text-align: center; }
    .header a { color: white; margin: 0 20px; text-decoration: none; }
    .container { max-width: 1200px; margin: 20px auto; padding: 0 20px; }
    .products { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; }
    .product { background: white; padding: 15px; text-align: center; }
    .product img { width: 100%; height: 200px; object-fit: contain; }
    .product h3 { font-size: 14px; margin: 10px 0; }
    .product .price { color: red; font-weight: bold; margin: 10px 0; }
    .product a { background: red; color: white; padding: 8px 15px; text-decoration: none; display: inline-block; }
</style>
</head>
<body>
<div class="header">
    <a href="home">TRANG CHỦ</a>
    <c:forEach items="${categories}" var="cat">
        <a href="home?category_id=${cat.id}">${cat.name}</a>
    </c:forEach>
</div>

<div class="container">
    <h1>	
        <c:choose>
            <c:when test="${not empty selectedCategoryId}">
                <c:forEach items="${categories}" var="cat">
                    <c:if test="${cat.id == selectedCategoryId}">${cat.name}</c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>Tất cả sản phẩm</c:otherwise>
        </c:choose>
    </h1>

    <div class="products">
        <c:forEach items="${products}" var="p">
            <div class="product">
                <c:choose>
                    <c:when test="${not empty p.image}">
                        <img src="${pageContext.request.contextPath}/uploads/products/${p.image}" alt="${p.name}">
                    </c:when>
                    <c:otherwise>
                        <img src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg" alt="No image">
                    </c:otherwise>
                </c:choose>
                <h3>${p.name}</h3>
                <div class="price">
                    <fmt:formatNumber value="${p.price}" type="number"/>₫
                </div>
                <a href="home?action=detail&id=${p.id}">Chi tiết</a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>