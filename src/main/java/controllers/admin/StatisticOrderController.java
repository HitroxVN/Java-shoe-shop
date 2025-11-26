package controllers.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatisticDAO;

@WebServlet("/statistic/order")
public class StatisticOrderController extends HttpServlet {
private StatisticDAO  statisticDAO;
	
	@Override
	public void init() throws ServletException {
		statisticDAO = new StatisticDAO();
	}
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// tổng số đơn hàng
			req.setAttribute("tongSoDonHang", statisticDAO.tongSoDonHang());
			
			// trạng thái
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("Pending", statisticDAO.countOrderByStatus("pending"));
			map.put("Confirmed", statisticDAO.countOrderByStatus("confirmed"));
			map.put("Shipped", statisticDAO.countOrderByStatus("shipped"));
			map.put("Delivered", statisticDAO.countOrderByStatus("delivered"));
			map.put("Cancelled", statisticDAO.countOrderByStatus("cancelled"));
			
			req.setAttribute("status", map);
			
			RequestDispatcher rq = req.getRequestDispatcher("/views/statistic_order.jsp");
			rq.forward(req, resp);
		}
}
