<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
<h2>Quản lý loại sản phẩm</h2>
        
        <!-- Thông báo -->
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        <!-- Form tìm kiếm -->
	<form action="CategoryController" method="get" style="margin: 20px 0;">
		<input type="hidden" name="action" value="search"> <input
			type="text" name="keyword" placeholder="Nhập tên loại cần tìm..."
			value="${param.keyword}" />
		<button type="submit" class="btn btn-primary">Tìm kiếm</button>
		<a href="CategoryController" class="btn btn-secondary">Reset</a>
	</form>
        <!-- Actions -->
        <div class="actions">
            <a href="CategoryController?action=add" class="btn btn-primary">+ Thêm loại sản phẩm mới</a>
            <a href="ProductController" class="btn btn-secondary">← Quay lại quản lý sản phẩm</a>
        </div>
        
        <!-- Bảng loại sản phẩm -->
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th style="width: 100px;">ID</th>
                    <th>Tên loại sản phẩm</th>
                    <th style="width: 200px;">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty listCategories}">
                        <tr>
                            <td colspan="3" class="no-data">Không có loại sản phẩm nào</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${listCategories}" var="category">
                            <tr>
                                <td>${category.id}</td>
                                <td><strong>${category.name}</strong></td>
                                <td>
                                    <div class="action-btns">
                                        <a href="CategoryController?action=edit&id=${category.id}" class="btn btn-warning">Sửa</a>
                                        <a href="CategoryController?action=delete&id=${category.id}" 
                                           class="btn btn-danger"
                                           onclick="return confirm('Bạn có chắc muốn xóa loại sản phẩm này?')">Xóa</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <a href="admin">Trang quản lý</a>
</body>
</html>