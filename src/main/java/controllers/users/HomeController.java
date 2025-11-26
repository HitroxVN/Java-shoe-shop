package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDao;
import dao.ProductDao;
import models.Categories;
import models.Products;
import models.Users;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;
	private CategoryDao categoryDao;

	@Override
	public void init() {
		productDao = new ProductDao();
		categoryDao = new CategoryDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("detail".equals(action)) {
			showProductDetail(request, response);
		} else {
			showProducts(request, response);
		}
	}

	private void showProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer categoryId = null;
		try {
			String catId = request.getParameter("category_id");
			if (catId != null && !catId.equals("0")) {
				categoryId = Integer.parseInt(catId);
			}
		} catch (Exception e) {
		}

		Users session = (Users) request.getSession().getAttribute("user");
		
		request.setAttribute("user", session);
		List<Products> products = productDao.search(null, categoryId);
		List<Categories> categories = categoryDao.getAll();

		request.setAttribute("products", products);
		request.setAttribute("categories", categories);
		request.setAttribute("selectedCategoryId", categoryId);

		request.getRequestDispatcher("views/home/products.jsp").forward(request, response);
	}

	private void showProductDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Products product = productDao.getProductById(id);

			if (product == null) {
				response.sendRedirect("home");
				return;
			}

			request.setAttribute("product", product);
			request.getRequestDispatcher("views/home/detail.jsp").forward(request, response);

		} catch (Exception e) {
			response.sendRedirect("home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
