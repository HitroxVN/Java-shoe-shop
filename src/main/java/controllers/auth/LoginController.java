package controllers.auth;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDAO;
import models.Users;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({ "/LoginController", "/login", "/dangnhap" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthDAO authDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		authDao = new AuthDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("views/auth/dangnhap.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Users user = authDao.login(email, password);
		
		// lỗi đăng nhập
		if(user == null) {
			request.setAttribute("loi", "Lỗi đăng nhập");
			request.setAttribute("email", email);
			request.getRequestDispatcher("views/auth/dangnhap.jsp").forward(request, response);
			return;
		}
		
		// lưu session và đang nhập
		HttpSession session = request.getSession();
		int time = 30*24; // thời gian session (giờ) 30ngayf
		session.setMaxInactiveInterval(60*60 * time);
		session.setAttribute("user", user);
		
		// forward admin
		if(user.getRole().equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		
		if(user.getRole().equals("staff")) {
			response.sendRedirect(request.getContextPath() + "/staff");
			return;
		}
//		forward sang trang home
//		response.sendRedirect("index.jsp");
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
