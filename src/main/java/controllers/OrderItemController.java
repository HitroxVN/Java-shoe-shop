package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.OrderItemDAO;
import models.OrderItem;
import models.Users;

@WebServlet("/OrderItemController")
public class OrderItemController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy user từ session
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        // Nếu chưa đăng nhập → chuyển về login
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        OrderItemDAO dao = new OrderItemDAO();
        List<OrderItem> list = null;
        boolean canEdit = false; // quyền chỉnh sửa

        try {
            String role = user.getRole().toLowerCase();

            if (role.equals("admin")) {
                // Admin → xem tất cả, có quyền quản lý
                list = dao.getAllOrderItems();
                canEdit = true;
            } else if (role.equals("staff")) {
                // Staff → xem tất cả nhưng không được edit/delete
                list = dao.getAllOrderItems();
                canEdit = false;
            } else {
                // User → chỉ xem order items của mình
                list = dao.getOrderItemsByUser(user.getId());
                canEdit = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gửi dữ liệu sang JSP
        request.setAttribute("items", list);
        request.setAttribute("canEdit", canEdit);
        request.getRequestDispatcher("order_items.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
