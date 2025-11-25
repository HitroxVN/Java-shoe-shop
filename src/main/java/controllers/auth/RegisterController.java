package controllers.auth;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthDAO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet({ "/RegisterController", "/register", "/dangky" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthDAO authDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
		request.getRequestDispatcher("views/auth/dangky.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		
		// check repass
		if(!password.equals(repassword)) {
			request.setAttribute("loi", "Nhập lại mật khẩu không đúng");
			request.setAttribute("email", email);
			request.getRequestDispatcher("views/auth/dangky.jsp").forward(request, response);
			return;
		}
		
		// check duplicate email
		if(authDao.checkEmail(email)) {
			request.setAttribute("loi", "Trùng email");
			request.setAttribute("email", email);
			request.getRequestDispatcher("views/auth/dangky.jsp").forward(request, response);
			return;
		}
		
		// đang ký
		if(authDao.register(email, password)) {
			request.setAttribute("thanhcong", "Đăng ký tài khoản thành công");
			request.getRequestDispatcher("views/auth/dangnhap.jsp").forward(request, response);
		} else {
			request.setAttribute("loi", "Lỗi đăng ký");
			request.setAttribute("email", email);
			request.getRequestDispatcher("views/auth/dangky.jsp").forward(request, response);
		}
		
		
	}

}
