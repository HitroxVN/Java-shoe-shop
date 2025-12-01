package controllers.admin;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatisticDAO;

@WebServlet("/statistic/home")
public class StatisticHomeController extends HttpServlet {
	private StatisticDAO  statisticDAO;
	
	@Override
	public void init() throws ServletException {
		statisticDAO = new StatisticDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// tổng doanh thu
		req.setAttribute("doanhThu", statisticDAO.doanhThu());
		
		//tổng số đơn hàng
		req.setAttribute("tongDonHang", statisticDAO.tongSoDonHang());
		
		
		//tổng số mặt hàng
		req.setAttribute("tongSoMatHang", statisticDAO.tongSanPham());
		
		//tổng số tài khoản
		req.setAttribute("tongSoTaiKhoan", statisticDAO.tongTaiKhoan());
		
		RequestDispatcher rq = req.getRequestDispatcher("/views/admin/stats/statistic_home.jsp");
		rq.forward(req, resp);
		
	}
}
