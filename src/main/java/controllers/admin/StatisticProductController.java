package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatisticDAO;
import models.ProductStatistic;
import models.Products;

@WebServlet("/statistic/product")
public class StatisticProductController extends HttpServlet {
private StatisticDAO  statisticDAO;
	
	@Override
	public void init() throws ServletException {
		statisticDAO = new StatisticDAO();
	}
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			//tổng số mặt hàng
			req.setAttribute("tongSoMatHang", statisticDAO.tongSanPham());
			
			// tổng số sản phẩm
			req.setAttribute("tongSoSanPham", statisticDAO.tongSanPhamTrongKho());
			
			
			// tổng số sản phẩm đã bán
			req.setAttribute("tongSoSanPhamBan", statisticDAO.tongSanPhamDaBan());
			
			// top 3 sản phẩm bán chạy nhất
			List<ProductStatistic> listTopProduct = statisticDAO.sanPhanBanChay();
			req.setAttribute("top3SanPham", listTopProduct);
			
			// những sản phẩm có số lượng dưới 30 sp
			List<Products> listProductLittle = statisticDAO.sanPhamTonThap();
			req.setAttribute("sanPhamIt", listProductLittle);
			
			RequestDispatcher rq = req.getRequestDispatcher("/views/admin/stats/statistic_product.jsp");
			rq.forward(req, resp);
			
		}
}
