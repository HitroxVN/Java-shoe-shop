package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderItemDAO;
import models.OrderItem;

@WebServlet("/OrderItemController")
public class OrderItemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Thay vì lấy từ request, ta hardcode orderId để chạy trực tiếp
//        int orderId = 1; // đổi thành ID đơn hàng bạn muốn test
    	String oid = request.getParameter("id");
    	int orderId = Integer.parseInt(oid);

        try {
            OrderItemDAO dao = new OrderItemDAO();
            List<OrderItem> items = dao.getItemsByOrderId(orderId);

            request.setAttribute("items", items);
            request.setAttribute("orderId", orderId); // để hiển thị orderId nếu muốn
            request.getRequestDispatcher("views/order_items.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy dữ liệu đơn hàng");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
