package utils;

import java.util.ArrayList;
import java.util.List;

import models.Categories;
import models.Products;

public class ProductValidation {
	// Validate sản phẩm
	public static List<String> validateProduct(Products product) {
		List<String> errors = new ArrayList<>();

		if (product.getName() == null || product.getName().trim().isEmpty()) {
			errors.add("Tên sản phẩm không được để trống.");
		} else if (product.getName().length() > 255) {
			errors.add("Tên sản phẩm không được vượt quá 255 ký tự.");
		}

		if (product.getCategory_id() <= 0) {
			errors.add("Vui lòng chọn loại sản phẩm.");
		}

		if (product.getPrice() < 0) {
			errors.add("Giá sản phẩm phải lớn hơn hoặc bằng 0.");
		}

		if (product.getPrice() > 999999999.99) {
			errors.add("Giá sản phẩm không hợp lệ.");
		}

		if (product.getStock() < 0) {
			errors.add("Số lượng tồn kho phải lớn hơn hoặc bằng 0.");
		}

		if (product.getSize() != null && product.getSize().length() > 100) {
			errors.add("Size không được vượt quá 100 ký tự.");
		}

		return errors;
	}

	// Validate loại sản phẩm
	public static List<String> validateCategory(Categories category) {
		List<String> errors = new ArrayList<>();

		if (category.getName() == null || category.getName().trim().isEmpty()) {
			errors.add("Tên loại sản phẩm không được để trống.");
		} else if (category.getName().length() > 100) {
			errors.add("Tên loại sản phẩm không được vượt quá 100 ký tự.");
		}

		return errors;
	}

	// Validate file upload
	public static String validateImage(String fileName) {
		if (fileName == null || fileName.isEmpty()) {
			return null; // Cho phép không upload ảnh
		}

		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			extension = fileName.substring(i + 1).toLowerCase();
		}

		List<String> allowedExtensions = List.of("jpg", "jpeg", "png", "gif");
		if (!allowedExtensions.contains(extension)) {
			return "Chỉ chấp nhận file ảnh định dạng: jpg, jpeg, png, gif";
		}

		return null; // Valid
	}
}