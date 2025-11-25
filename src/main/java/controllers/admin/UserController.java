package controllers.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthDAO;
import dao.UserDAO;
import models.Users;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin/users")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private AuthDAO authDao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDao = new UserDAO();
		authDao = new AuthDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String page = request.getParameter("action");
		if(page == null) page = "list";
		
		switch (page) {
		case "add":
			FormAdd(request, response);
			break;
		case "edit":
			FormEdit(request, response);
			break;
		case "search":
			search(request, response);
			break;
		case "role_filter":
			role_filter(request, response);
			break;
		default:
			listUser(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		doGet(request, response);
		String page = request.getParameter("action");
		if(page == null) page = "list";
		
		switch (page) {
		case "delete":
			delete(request, response);
			break;
		case "edit":
			edit(request, response);
			break;
		case "add":
			add(request, response);
			break;
		default:
			listUser(request, response);
			break;
		}
	}
	
	// lấy tất cả user
	private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Users> list = userDao.getAll();
		request.setAttribute("users", list);
		request.getRequestDispatcher("/views/admin/users/index.jsp").forward(request, response);
	}
	
	// gọi form thêm user
	private void FormAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/users/add.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String email = request.getParameter("email");
		Users u = new Users();
		u.setFull_name(request.getParameter("full_name"));
		u.setEmail(email);
		u.setPassword(request.getParameter("password"));
		u.setPhone(request.getParameter("phone"));
		u.setAddress(request.getParameter("address"));
		u.setRole(request.getParameter("role"));
		
		Map<String, String> validate = utils.Validate.validateUsersAdminAdd(u);
		
//		kiểm tra dữ liệu nhập
		if(validate != null && !validate.isEmpty()) {
			request.setAttribute("loi", validate);
			request.setAttribute("inputAdd", u);
			request.getRequestDispatcher("/views/admin/users/add.jsp").forward(request, response);
			return;
		}
		
//		kiểm tra email trùng
		if(authDao.checkEmail(email)) {
			request.setAttribute("loiEmail", "Email đã tồn tại.");
			request.setAttribute("inputAdd", u);
			request.getRequestDispatcher("/views/admin/users/add.jsp").forward(request, response);
			return;
		}
		
//		ok
		if(userDao.create(u)) {
			request.setAttribute("thanhcong", "Tạo tài khoản người dùng thành công.");
		} else {
			request.setAttribute("loi", "Tạo tài khoản người dùng thất bại!");
		}
		listUser(request, response);
	}
	
	// gọi form sửa user
	private void FormEdit(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
			String sid = request.getParameter("user_id");
			int uid = Integer.parseInt(sid);
			
			Users user = userDao.getById(uid);
			request.setAttribute("inputEdit", user);
			request.getRequestDispatcher("/views/admin/users/edit.jsp").forward(request, response);
		}
	
	//  post cập nhập user
	private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("user_id"));
		String email = request.getParameter("email");
		Users u = userDao.getById(uid);
		u.setId(uid);
		u.setFull_name(request.getParameter("full_name"));
		u.setEmail(email);
		u.setPhone(request.getParameter("phone"));
		u.setAddress(request.getParameter("address"));
		u.setRole(request.getParameter("role"));
		
		Map<String, String> validate = utils.Validate.validateUsersAdminEdit(u);
		
//		kiểm tra dữ liệu nhập
		if(validate != null && !validate.isEmpty()) {
			request.setAttribute("user_id", uid);
			request.setAttribute("loi", validate);
			request.setAttribute("inputEdit", u);
			request.getRequestDispatcher("/views/admin/users/edit.jsp").forward(request, response);
			return;
		}
		
//		kiểm tra email trùng
		if(!email.equals(u.getEmail()) && authDao.checkEmail(email)) {
			request.setAttribute("user_id", uid);
			request.setAttribute("loiEmail", "Email đã tồn tại.");
			request.setAttribute("inputEdit", u);
			request.getRequestDispatcher("/views/admin/users/edit.jsp").forward(request, response);
			return;
		}
		
		if(userDao.update(u)) {
			request.setAttribute("thanhcong", "Cập nhập thông tin người dùng thành công.");
		} else {
			request.setAttribute("loi", "Cập nhập thông tin thất bại!");
		}
		listUser(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String stringid = request.getParameter("user_id");
		int uid = Integer.parseInt(stringid);
		
		if(userDao.delete(uid)) {
			request.setAttribute("thanhcong", "Xoá tài khoản người dùng thành công.");
		} else {
			request.setAttribute("loi", "Xoá tài khoản người dùng thất bại!");
		}
		listUser(request, response);
	}
	
	// tìm kiếm
	private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String key = request.getParameter("keyword");
		if(key == null) {
			listUser(request, response);
			return;
		}
		
		List<Users> list = userDao.search(key);
		
		request.setAttribute("users", list);
		request.getRequestDispatcher("/views/admin/users/index.jsp").forward(request, response);
	}
	
	private void role_filter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String role = request.getParameter("role");
		
		if(role == null) {
			listUser(request, response);
			return;
		}
		
		List<Users> list = userDao.getByRole(role);
		request.setAttribute("users", list);
		request.getRequestDispatcher("/views/admin/users/index.jsp").forward(request, response);
		
		
	}
}
