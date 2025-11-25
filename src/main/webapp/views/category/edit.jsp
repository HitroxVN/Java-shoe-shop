<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-style.css">
</head>
<body>
 <h2>Sửa loại sản phẩm</h2>
        
        <!-- Hiển thị lỗi -->
        <c:if test="${not empty errors}">
            <div class="error-list">
                <strong>Vui lòng sửa các lỗi sau:</strong>
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        
        <form action="CategoryController" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="${category.id}">
            
            <div class="form-group">
                <label for="name">Tên loại sản phẩm <span style="color:red">*</span></label>
                <input type="text" id="name" name="name" value="${category.name}" required>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Cập nhật</button>
                <a href="CategoryController" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
</body>
</html>