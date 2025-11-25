package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CategoryDao;
import dao.ProductDao;
import models.Categories;
import models.Products;
import utils.ProductValidation;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "uploads/products";

	private ProductDao productDao;
	private CategoryDao categoryDao;

	@Override
	public void init() throws ServletException {
		productDao = new ProductDao();
		categoryDao = new CategoryDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		if (action == null) {
			action = "list";
		}

		switch (action) {
		case "list":
			listProducts(request, response);
			break;
		case "add":
			showAddForm(request, response);
			break;
		case "edit":
			showEditForm(request, response);
			break;
		case "delete":
			deleteProduct(request, response);
			break;
		case "search":
			searchProducts(request, response);
			break;
		default:
			listProducts(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		if (action == null) {
			action = "list";
		}
		switch (action) {
		case "add":
			addProduct(request, response);
			break;
		case "edit":
			updateProduct(request, response);
			break;
		default:
			listProducts(request, response);
			break;
		}
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Products> listProducts = productDao.getAll();
		List<Categories> listCategories = categoryDao.getAll();

		request.setAttribute("listProducts", listProducts);
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("views/product/list.jsp").forward(request, response);
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categories> listCategories = categoryDao.getAll();
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("views/product/add.jsp").forward(request, response);
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Lấy dữ liệu từ form
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String priceStr = request.getParameter("price");
		String categoryIdStr = request.getParameter("category_id");
		String stockStr = request.getParameter("stock");

		String[] sizes = request.getParameterValues("sizes");

		String sizeString = "";
		if (sizes != null) {
			sizeString = String.join(",", sizes);
		}

		// Upload file
		String fileName = uploadFile(request);

		try {
			int categoryId = Integer.parseInt(categoryIdStr);
			double price = Double.parseDouble(priceStr);
			int stock = Integer.parseInt(stockStr);

			Products product = new Products(categoryId, name, description, price, fileName, sizeString, stock);

			// Validate
			List<String> errors = ProductValidation.validateProduct(product);
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				request.setAttribute("product", product);
				List<Categories> listCategories = categoryDao.getAll();
				request.setAttribute("listCategories", listCategories);
				request.getRequestDispatcher("views/product/add.jsp").forward(request, response);
				return;
			}

			boolean success = productDao.addProduct(product);
			if (success) {
				request.setAttribute("message", "Thêm sản phẩm thành công!");
			} else {
				request.setAttribute("error", "Thêm sản phẩm thất bại!");
			}

		} catch (NumberFormatException e) {
			request.setAttribute("error", "Dữ liệu không hợp lệ!");
		}

		listProducts(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Products product = productDao.getProductById(id);
		List<Categories> listCategories = categoryDao.getAll();

		request.setAttribute("product", product);
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("views/product/edit.jsp").forward(request, response);
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String priceStr = request.getParameter("price");
		String categoryIdStr = request.getParameter("category_id");
		String stockStr = request.getParameter("stock");
		String oldImage = request.getParameter("old_image");

		String[] sizes = request.getParameterValues("sizes");

		String sizeString = "";
		if (sizes != null) {
			sizeString = String.join(",", sizes);
		}

		// Upload file mới (nếu có)
		String fileName = uploadFile(request);
		if (fileName == null || fileName.isEmpty()) {
			fileName = oldImage; // Giữ ảnh cũ
		}

		try {
			int id = Integer.parseInt(idStr);
			int categoryId = Integer.parseInt(categoryIdStr);
			double price = Double.parseDouble(priceStr);
			int stock = Integer.parseInt(stockStr);

			Products product = new Products(id, categoryId, name, description, price, fileName, sizeString, stock);

			// Validate
			List<String> errors = ProductValidation.validateProduct(product);
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				request.setAttribute("product", product);
				List<Categories> listCategories = categoryDao.getAll();
				request.setAttribute("listCategories", listCategories);
				request.getRequestDispatcher("views/product/edit.jsp").forward(request, response);
				return;
			}

			boolean success = productDao.updateProduct(product);
			if (success) {
				request.setAttribute("message", "Cập nhật sản phẩm thành công!");
			} else {
				request.setAttribute("error", "Cập nhật sản phẩm thất bại!");
			}

		} catch (NumberFormatException e) {
			request.setAttribute("error", "Dữ liệu không hợp lệ!");
		}

		listProducts(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean success = productDao.deleteProduct(id);

		if (success) {
			request.setAttribute("message", "Xóa sản phẩm thành công!");
		} else {
			request.setAttribute("error", "Xóa sản phẩm thất bại!");
		}

		listProducts(request, response);
	}

	private void searchProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String categoryIdStr = request.getParameter("category_id");

		Integer categoryId = null;
		if (categoryIdStr != null && !categoryIdStr.isEmpty() && !categoryIdStr.equals("0")) {
			categoryId = Integer.parseInt(categoryIdStr);
		}

		List<Products> listProducts = productDao.search(keyword, categoryId);
		List<Categories> listCategories = categoryDao.getAll();

		request.setAttribute("listProducts", listProducts);
		request.setAttribute("listCategories", listCategories);
		request.setAttribute("keyword", keyword);
		request.setAttribute("selectedCategoryId", categoryId);
		request.getRequestDispatcher("views/product/list.jsp").forward(request, response);
	}

	// Hàm upload file
	private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
		Part filePart = request.getPart("image");

		if (filePart == null || filePart.getSize() == 0) {
			return null;
		}

		String fileName = extractFileName(filePart);

		// Validate image
		String validationError = ProductValidation.validateImage(fileName);
		if (validationError != null) {
			request.setAttribute("error", validationError);
			return null;
		}

		// Tạo tên file unique
		String timeStamp = String.valueOf(System.currentTimeMillis());
		fileName = timeStamp + "_" + fileName;

		// Đường dẫn thực tế trên server
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Lưu file
		String filePath = uploadPath + File.separator + fileName;
		filePart.write(filePath);

		return fileName;
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}
