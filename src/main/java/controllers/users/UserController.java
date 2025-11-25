package controllers.users;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDAO;
import dao.UserDAO;
import models.Users;

/**
 * Servlet implementation class UserController
 */
@WebServlet({ "/users", "/thongtin" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
	private AuthDAO authDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDAO();
		authDao = new AuthDAO();
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		info(request, response);
	}
	
	private void info(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Users session = (Users) request.getSession().getAttribute("user");
		request.setAttribute("user", session);
		request.getRequestDispatcher("views/user/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Users session = (Users) request.getSession().getAttribute("user");
		Users u = new Users();
		
		String email = request.getParameter("email");
		u.setId(session.getId());
		u.setFull_name(request.getParameter("full_name"));
		u.setEmail(email);
		u.setPhone(request.getParameter("phone"));
		u.setAddress(request.getParameter("address"));
		u.setPassword(session.getPassword());
		u.setRole(session.getRole());
		
		Map<String, String> validate = utils.Validate.validateUser(u);
		
//		kiểm tra dữ liệu nhập
		if(validate != null && !validate.isEmpty()) {
			request.setAttribute("loi", validate);
			request.setAttribute("inputAdd", u);
			request.getRequestDispatcher("views/user/index.jsp").forward(request, response);
			return;
		}
		
//		kiểm tra email trùng
		if(authDao.checkEmail(email)) {
			request.setAttribute("loiEmail", "Email đã tồn tại.");
			request.setAttribute("inputAdd", u);
			request.getRequestDispatcher("views/user/index.jsp").forward(request, response);
			return;
		}
		
		if(userDao.update(u)) {
			// set session mơiaa
			HttpSession sessionNew = request.getSession();
			sessionNew.setAttribute("user", u);
			request.setAttribute("thanhcong", "Cập nhập thông tin người dùng thành công.");
		} else {
			request.setAttribute("loicapnhap", "Cập nhập thông tin thất bại!");
		}
		info(request, response);
	}

}
