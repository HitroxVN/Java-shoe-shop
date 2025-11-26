package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatisticDAO;
import models.CategoryStatistic;

@WebServlet("/statistic/categories")
public class StatisticCategoryController extends HttpServlet {
private StatisticDAO  statisticDAO;
	
	@Override
	public void init() throws ServletException {
		statisticDAO = new StatisticDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// tổng số thương hiệu
		req.setAttribute("tongSoThuongHieu", statisticDAO.soThuongHieu());
		
		//top 3 thương hiệu bán chạy nhất
		List<CategoryStatistic> listCategory = statisticDAO.topThuongHieuBanChay();
		req.setAttribute("top3ThuongHieu", listCategory);
		
		// các thương hiệu có số lượng sản phẩm dưới 100
		List<CategoryStatistic> listLittle = statisticDAO.thuongHieuTonThap();
		req.setAttribute("thuonHieuIt", listLittle);
		
		RequestDispatcher rq = req.getRequestDispatcher("/views/statistic_categories.jsp");
		rq.forward(req, resp);
	}

}
