<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    .breadcrumb { max-width: 1200px; margin: 20px auto; padding: 0 20px; }
    .breadcrumb a { text-decoration: none; color: #333; }
    .container { max-width: 1200px; margin: 20px auto; padding: 20px; background: white; }
    .detail { display: grid; grid-template-columns: 1fr 1fr; gap: 40px; }
    .detail img { width: 100%; }
    .info h1 { font-size: 24px; margin-bottom: 15px; }
    .info .price { font-size: 28px; color: red; font-weight: bold; margin: 15px 0; }
    .info .brand { color: #666; margin-bottom: 20px; }
    .sizes { margin: 20px 0; }
    .sizes button { padding: 10px 20px; margin: 5px; border: 1px solid #ddd; background: white; cursor: pointer; }
    .sizes button:hover { background: #f0f0f0; }
    .sizes button.selected { border: 2px solid red; background: #ffe6e6; }
    .quantity { margin: 20px 0; }
    .quantity button { padding: 8px 15px; border: 1px solid #ddd; background: white; cursor: pointer; }
    .quantity input { width: 60px; text-align: center; padding: 8px; border: 1px solid #ddd; }
    .buttons { margin-top: 20px; }
    .buttons button { padding: 15px 30px; margin-right: 10px; font-size: 16px; cursor: pointer; border: none; }
    .btn-cart { background: white; color: red; border: 2px solid red; }
    .btn-buy { background: red; color: white; }
</style>
</head>
<body>
<div class="header">
    <a href="home">TRANG CHỦ</a>
    <a href="home?category_id=${product.category_id}">${product.categoryName}</a>
</div>

<div class="breadcrumb">
    <a href="home">Trang chủ</a> > 
    <a href="home?category_id=${product.category_id}">${product.categoryName}</a> > 
    ${product.name}
</div>

<div class="container">
	
    <div class="detail">
        <div class="image">
            <c:choose>
                <c:when test="${not empty product.image}">
                    <img src="${pageContext.request.contextPath}/uploads/products/${product.image}" alt="${product.name}">
                </c:when>
                <c:otherwise>
                    <img src="https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg" alt="No image">
                </c:otherwise>
            </c:choose>
        </div>

        <div class="info">
            <h1>${product.name}</h1>
            
            <div class="brand">
                Thương hiệu: ${product.categoryName} | Kho: <strong>Còn hàng</strong>
            </div>

            <div class="price">
                <fmt:formatNumber value="${product.price}" type="number"/>₫
            </div>

            <p><strong>Fullbox, Cam kết 100% chính hãng, Phát hiện hàng giả xin đền 10 lần tiền.</strong></p>
            
            <p>Ship COD toàn quốc | Miễn phí đổi size, đổi màu trong 1 tuần !!!</p>

            <div class="sizes">
                <strong>Kích thước:</strong><br><br>
                <c:forEach items="${fn:split(product.size, ',')}" var="size">
                    <button onclick="selectSize(this)">${size}</button>
                </c:forEach>
            </div>
	
	<form action="cart" method="post">
            <div class="quantity">
                <strong>Số lượng:</strong>
				
                <input name="quantity" type="number" id="qty" value="1" min="1" max="${product.stock}">

            </div>

            <div class="buttons">
            <input type="hidden" name="userId" value="${user.id }">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="productId" value="${product.id}">
            
            <button class="btn-cart" type="submit">Thêm Vào Giỏ Hàng</button>
            
                
            </div>
            
            </form>
        </div>
    </div>

    <div style="margin-top: 30px; border-top: 2px solid #ddd; padding-top: 20px;">
        <h2>Mô tả sản phẩm</h2>
        <p>${product.description}</p>
        
        <p><strong>Số lượng còn:</strong> ${product.stock} sản phẩm</p>
    </div>
</div>

<script>
	function selectSize(btn) {
	    var buttons = document.querySelectorAll('.sizes button');
	    buttons.forEach(function(b) {
	        b.classList.remove('selected');
	    });
	    btn.classList.add('selected');
	}
    function increaseQty() {
        var qty = document.getElementById('qty');
        var max = parseInt(qty.max);
        if (parseInt(qty.value) < max) {
            qty.value = parseInt(qty.value) + 1;
        }
    }
    function decreaseQty() {
        var qty = document.getElementById('qty');
        if (parseInt(qty.value) > 1) {
            qty.value = parseInt(qty.value) - 1;
        }
    }
</script>
</body>
</html>