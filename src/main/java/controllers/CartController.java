package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import models.Carts;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDao cartDao = new CartDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Tạm test userId = 1 (sau này lấy từ session)
        List<Carts> cartList = cartDao.getCartByUserId(1);

        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("/views/cart/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            int userId = Integer.parseInt(request.getParameter("userId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Dùng constructor rỗng và set các field
            Carts cart = new Carts();
            cart.setId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);

            cartDao.addToCart(cart);
            response.sendRedirect("cart");

        } else if ("update".equals(action)) {

            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            cartDao.updateQuantity(cartId, quantity);
            response.sendRedirect("cart");

        } else if ("remove".equals(action)) {

            int cartId = Integer.parseInt(request.getParameter("cartId"));
            cartDao.removeFromCart(cartId);
            response.sendRedirect("cart");

        }
    }
}
