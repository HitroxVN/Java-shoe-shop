<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Quản lý sản phẩm</h2>
        
<!-- Thông báo -->
<c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-error">${error}</div>
</c:if>

<!-- Actions -->
<div class="actions">
    <a href="ProductController?action=add" class="btn btn-primary">+ Thêm sản phẩm mới</a>
    
    <form action="ProductController" method="get" class="search-form">
        <input type="hidden" name="action" value="search">
        <input type="text" name="keyword" placeholder="Tìm kiếm theo tên..." value="${keyword}">
        <select name="category_id">
            <option value="0">-- Tất cả loại --</option>
            <c:forEach items="${listCategories}" var="cat">
                <option value="${cat.id}" ${cat.id == selectedCategoryId ? 'selected' : ''}>
                    ${cat.name}
                </option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-success">Tìm kiếm</button>
    </form>
</div>

<!-- Bảng sản phẩm -->
<table border="1" cellpadding="5" cellspacing="0">
    <thead>
        <tr>
            <th>ID</th>
            <th>Ảnh</th>
            <th>Tên sản phẩm</th>
            <th>Loại</th>
            <th>Giá</th>
            <th>Size</th>
            <th>Tồn kho</th>
            <th>Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty listProducts}">
                <tr>
                    <td colspan="8" class="no-data">Không có sản phẩm nào</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${listProducts}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>
                            <c:if test="${not empty product.image}">
                                <img src="${pageContext.request.contextPath}/uploads/products/${product.image}" 
                                     alt="${product.name}" class="product-image">
                            </c:if>
                        </td>
                        <td><strong>${product.name}</strong></td>
                        <td>${product.categoryName}</td>
                        <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫"/></td>
                        <td>
                            <c:if test="${not empty product.size}">
                                <div class="size-tags">
                                    <c:forEach items="${fn:split(product.size, ',')}" var="size">
                                        <span class="size-tag">${size}</span>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </td>
                        <td>${product.stock}</td>
                        <td>
                            <div class="action-btns">
                                <a href="ProductController?action=edit&id=${product.id}" class="btn btn-warning">Sửa</a>
                                <a href="ProductController?action=delete&id=${product.id}" 
                                   class="btn btn-danger"
                                   onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này?')">Xóa</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<div style="margin-top: 20px;">
    <a href="CategoryController" class="btn btn-primary">Quản lý loại sản phẩm (danh mục)</a><br>
    <a href="admin">Trang quản lý</a>
</div>
</body>
</html>