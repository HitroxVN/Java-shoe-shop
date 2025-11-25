package controllers.staff;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.Users;

/**
 * Servlet implementation class StaffController
 */
@WebServlet({ "/staff", "/nhanvien" })
public class StaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDAO();
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
		
		String page = request.getParameter("action");
		if(page == null) page = "home";
		switch (page) {
		case "search":
			search(request, response);
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
		doGet(request, response);
		
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Users> list = userDao.getByRole("customer");
		request.setAttribute("users", list);
		request.getRequestDispatcher("views/staff/index.jsp").forward(request, response);
	}
	
	// tìm kiếm
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("keyword");
		if (key == null) {
			listUser(request, response);
			return;
		}

		List<Users> list = userDao.search(key);
		
		// chỉ hiện khách hàng
		list.removeIf(u -> !"customer".equals(u.getRole()));

		request.setAttribute("users", list);
		request.getRequestDispatcher("views/staff/index.jsp").forward(request, response);
	}

}
