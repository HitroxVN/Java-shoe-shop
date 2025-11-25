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
<h2>Thêm sản phẩm mới</h2>
        
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
        
        <form action="ProductController" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add">
            
            <div class="form-group">
                <label for="name">Tên sản phẩm <span style="color:red">*</span></label>
                <input type="text" id="name" name="name" value="${product.name}" required>
            </div>
            
            <div class="form-group">
                <label for="category_id">Loại sản phẩm <span style="color:red">*</span></label>
                <select id="category_id" name="category_id" required>
                    <option value="">-- Chọn loại sản phẩm --</option>
                    <c:forEach items="${listCategories}" var="cat">
                        <option value="${cat.id}" ${cat.id == product.category_id ? 'selected' : ''}>
                            ${cat.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="description">Mô tả</label>
                <textarea id="description" name="description">${product.description}</textarea>
            </div>
            
            <div class="form-group">
                <label for="price">Giá (VNĐ) <span style="color:red">*</span></label>
                <input type="number" id="price" name="price" value="${product.price}" step="1000" min="0" required>
            </div>
            
            <div class="form-group">
			    <label>Size</label>
			    <div>
			        <c:forEach var="sz" begin="36" end="45">
			            <label style="margin-right: 10px;">
			                <input type="checkbox" name="sizes" value="${sz}">
			                ${sz}
			            </label>
			        </c:forEach>
			    </div>
			</div>
            
            <div class="form-group">
                <label for="stock">Số lượng tồn kho <span style="color:red">*</span></label>
                <input type="number" id="stock" name="stock" value="${product.stock}" min="0" required>
            </div>
            
            <div class="form-group">
                <label for="image">Ảnh sản phẩm</label>
                <input type="file" id="image" name="image" accept="image/*">
                <small style="color: #666;">Chấp nhận: JPG, JPEG, PNG, GIF</small>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
                <a href="ProductController" class="btn btn-secondary">Hủy</a>
            </div>
        </form>
</body>
</html>