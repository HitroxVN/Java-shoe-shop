package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import models.Carts;
import models.Users;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartDao cartDao = new CartDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Tạm test userId = 1 (sau này lấy từ session)
    	
    	Users session = (Users) request.getSession().getAttribute("user");
    	int uid = session.getId();
        List<Carts> cartList = cartDao.getCartByUserId(uid);

        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("/views/users/cart/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
        	

            int userId = Integer.parseInt(request.getParameter("userId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
//            System.out.println(userId);

            // Dùng constructor rỗng và set các field
            Carts cart = new Carts();
            cart.setId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);

            cartDao.addToCart(cart);
//            request.getRequestDispatcher("views/cart/cart.jsp").forward(request, response);
//            response.sendRedirect("cart");
            response.sendRedirect(request.getContextPath() + "/cart");

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
