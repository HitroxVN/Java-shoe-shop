package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import models.Orders;

@WebServlet("/Odercontrollers")
public class Odercontrollers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Odercontrollers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // mặc định xem danh sách đơn hàng
        }

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

    // ==========================
    // HIỂN THỊ TẤT CẢ ĐƠN HÀNG
    // ==========================
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO dao = new OrderDAO();
        List<Orders> list = dao.getAllOrders();

        request.setAttribute("orders", list);
        request.getRequestDispatcher("views/order_list.jsp").forward(request, response);
    }

    
    // ==========================
    // XÁC NHẬN ĐƠN HÀNG
    // ==========================
    private void confirmOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        OrderDAO dao = new OrderDAO();
        boolean check = dao.updateStatus(id, "confirmed");

        if (check) {
            System.out.println("Xác nhận đơn hàng thành công!");
        } else {
            System.out.println("Lỗi khi xác nhận đơn hàng.");
        }

        response.sendRedirect("Odercontrollers?action=list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
