package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.OrderDAO;
import dao.OrderItemDAO;
import models.Carts;
import models.Orders;
import models.Users;
import models.Order_items;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDao cartDao = new CartDao();
    private OrderDAO orderDao = new OrderDAO();
    private OrderItemDAO orderItemDao = new OrderItemDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lấy userId từ session (tạm test userId = 1)
//        int userId = 1;
    	Users session = (Users) request.getSession().getAttribute("user");
    	int userId = session.getId();
        
        // Lấy danh sách sản phẩm trong giỏ hàng
        List<Carts> cartList = cartDao.getCartByUserId(userId);
        
        // Tính tổng tiền
        double totalAmount = 0;
        for (Carts cart : cartList) {
            totalAmount += cart.getProductPrice() * cart.getQuantity();
        }
        
        request.setAttribute("cartList", cartList);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("userId", userId);
        
        request.getRequestDispatcher("/views/users/checkout/checkout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Lấy dữ liệu từ form
            int userId = Integer.parseInt(request.getParameter("userId"));
            String shippingAddress = request.getParameter("shippingAddress");
            String paymentMethod = request.getParameter("paymentMethod");
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
            
            // Tạo đơn hàng
            Orders order = new Orders();
            order.setUser_id(userId);
            order.setShipping_address(shippingAddress);
            order.setTotal_amount(totalAmount);
            order.setPayment_method(paymentMethod);
            order.setStatus("pending");
            
            int orderId = orderDao.createOrder(order);
            
            if (orderId > 0) {
                // Lấy danh sách sản phẩm trong giỏ hàng
                List<Carts> cartList = cartDao.getCartByUserId(userId);
                
                // Thêm từng sản phẩm vào order_items
                for (Carts cart : cartList) {
                    Order_items orderItem = new Order_items();
                    orderItem.setOrder_id(orderId);
                    orderItem.setProduct_id(cart.getProductId());
                    orderItem.setQuantity(cart.getQuantity());
                    orderItem.setPrice(cart.getProductPrice());
                    
                    orderItemDao.addOrderItem(orderItem);
                }
                
                // Xóa tất cả sản phẩm khỏi giỏ hàng
                for (Carts cart : cartList) {
                    cartDao.removeFromCart(cart.getId());
                }
                
                // Chuyển hướng đến trang xác nhận
                response.sendRedirect("UserOrderController?action=orderDetail&id=" + orderId);
            } else {
                response.sendRedirect("checkout?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("checkout?error=true");
        }
    }
}
