package controllers.users;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import dao.OrderItemDAO;
import models.Orders;
import models.OrderItem;
import models.Users;

@WebServlet("/UserOrderController")
public class UserOrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Users currentUser = (Users) session.getAttribute("user"); // user đã login

        if (currentUser == null) {
        	response.sendRedirect(request.getContextPath() + "/login"); // chưa login
            return;
        }

        String action = request.getParameter("action");

        if ("orderList".equals(action)) {
            showOrderList(request, response, currentUser.getId());
        } else if ("orderDetail".equals(action)) {
            showOrderDetail(request, response, currentUser.getId());
        } else {
            // default redirect
            response.sendRedirect("UserOrderController?action=orderList");
        }
    }

    private void showOrderList(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {
        OrderDAO orderDao = new OrderDAO();
        List<Orders> orders = orderDao.getOrdersByUser(userId); // Lấy danh sách đơn hàng của user
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/user_orders.jsp").forward(request, response);
    }

    private void showOrderDetail(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {

        String oid = request.getParameter("id");
        if (oid == null || oid.isEmpty()) {
            response.sendRedirect("UserOrderController?action=orderList");
            return;
        }

        int orderId = 0;
        try {
            orderId = Integer.parseInt(oid);
        } catch (NumberFormatException e) {
            response.sendRedirect("UserOrderController?action=orderList");
            return;
        }

        // Lấy đơn hàng theo orderId
        OrderDAO orderDao = new OrderDAO();
        Orders order = orderDao.getOrderById(orderId);
        if (order == null || order.getUser_id() != userId) { // kiểm tra quyền truy cập
            response.sendRedirect("UserOrderController?action=orderList");
            return;
        }

        // Lấy chi tiết sản phẩm trong đơn hàng
        OrderItemDAO itemDao = new OrderItemDAO();
        List<OrderItem> items = itemDao.getItemsByOrderId(orderId);

        request.setAttribute("items", items);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("/views/user_order_items.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}