package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import models.Categories;
import utils.ProductValidation;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;

	@Override
	public void init() throws ServletException {
		categoryDao = new CategoryDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
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
	        listCategories(request, response);
	        break;
	    case "search":
	        searchCategories(request, response);
	        break;
	    case "add":
	        showAddForm(request, response);
	        break;
	    case "edit":
	        showEditForm(request, response);
	        break;
	    case "delete":
	        deleteCategory(request, response);
	        break;
	    default:
	        listCategories(request, response);
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
			addCategory(request, response);
			break;
		case "edit":
			updateCategory(request, response);
			break;
		default:
			listCategories(request, response);
			break;
		}
	}

	private void listCategories(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categories> listCategories = categoryDao.getAll();
		request.setAttribute("listCategories", listCategories);
		request.getRequestDispatcher("views/admin/category/list.jsp").forward(request, response);
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/admin/category/add.jsp").forward(request, response);
	}
	private void searchCategories(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");

		List<Categories> listCategories;

		if (keyword == null || keyword.trim().isEmpty()) {
			listCategories = categoryDao.getAll(); 
		} else {
			listCategories = categoryDao.getSearch(keyword);
		}

		request.setAttribute("listCategories", listCategories);
		request.setAttribute("keyword", keyword);

		request.getRequestDispatcher("views/admin/category/list.jsp").forward(request, response);
	}	
	
	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");

		Categories category = new Categories(name);

		// Validate
		List<String> errors = ProductValidation.validateCategory(category);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("category", category);
			request.getRequestDispatcher("views/admin/category/add.jsp").forward(request, response);
			return;
		}

		boolean success = categoryDao.addCategory(category);
		if (success) {
			request.setAttribute("message", "Thêm loại sản phẩm thành công!");
		} else {
			request.setAttribute("error", "Thêm loại sản phẩm thất bại!");
		}

		listCategories(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Categories category = categoryDao.getCategoryById(id);
		request.setAttribute("category", category);
		request.getRequestDispatcher("views/admin/category/edit.jsp").forward(request, response);
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		Categories category = new Categories(id, name);

		// Validate
		List<String> errors = ProductValidation.validateCategory(category);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("category", category);
			request.getRequestDispatcher("views/admin/category/edit.jsp").forward(request, response);
			return;
		}

		// Kiểm tra trùng tên danh mục (loại trừ chính id) - Option A behavior
		if (categoryDao.existsByNameExcludingId(name, id)) {
			request.setAttribute("error", "Tên danh mục đã tồn tại. Vui lòng chọn tên khác.");
			request.setAttribute("category", category);
			request.getRequestDispatcher("views/admin/category/edit.jsp").forward(request, response);
			return;
		}

		boolean success = categoryDao.updateCategory(category);
		if (success) {
			request.setAttribute("message", "Cập nhật loại sản phẩm thành công!");
		} else {
			request.setAttribute("error", "Cập nhật loại sản phẩm thất bại!");
		}

		listCategories(request, response);
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		// Kiểm tra xem loại sản phẩm có sản phẩm nào không
		if (categoryDao.hasProducts(id)) {
			request.setAttribute("error", "Không thể xóa loại sản phẩm này vì đang có sản phẩm!");
			listCategories(request, response);
			return;
		}

		boolean success = categoryDao.deleteCategory(id);

		if (success) {
			request.setAttribute("message", "Xóa loại sản phẩm thành công!");
		} else {
			request.setAttribute("error", "Xóa loại sản phẩm thất bại!");
		}

		listCategories(request, response);
	}

}