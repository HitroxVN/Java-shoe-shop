package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.OrderDAO;
import models.Orders;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listOrders(request, response);
                break;
            case "confirm":
                confirmOrder(request, response);
                break;
            default:
                listOrders(request, response);
                break;
        }
    }

    // Hiển thị tất cả đơn hàng
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO dao = new OrderDAO();
        List<Orders> list = dao.getAllOrders();

        request.setAttribute("orders", list);
        request.getRequestDispatcher("views/admin/orders/order_list.jsp").forward(request, response);
    }

    // Xác nhận đơn hàng
    private void confirmOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDAO dao = new OrderDAO();
            dao.updateStatus(id, "confirmed");


        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("OrderController?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
