package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.Users;

/**
 * Servlet implementation class AdminController
 */
@WebServlet({ "/AdminController", "/admin" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDao = new UserDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
			break;
		}
	}
	
	// lấy tất cả user
	private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Users> list = userDao.getAll();
		request.setAttribute("users", list);
		request.getRequestDispatcher("views/admin/index.jsp").forward(request, response);
	}
	
	// gọi form thêm user
	private void FormAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("views/admin/add.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Users u = new Users();
		
		u.setFull_name(request.getParameter("full_name"));
		u.setEmail(request.getParameter("email"));
		u.setPassword(request.getParameter("password"));
		u.setPhone(request.getParameter("phone"));
		u.setAddress(request.getParameter("address"));
		u.setRole(request.getParameter("role"));
		
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
			request.setAttribute("user", user);
			request.getRequestDispatcher("views/admin/edit.jsp").forward(request, response);
		}
	
	//  post cập nhập user
	private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		int uid = Integer.parseInt(request.getParameter("user_id"));
		Users u = userDao.getById(uid);
		
		u.setFull_name(request.getParameter("full_name"));
		u.setEmail(request.getParameter("email"));
		u.setPhone(request.getParameter("phone"));
		u.setAddress(request.getParameter("address"));
		u.setRole(request.getParameter("role"));
		
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

}
