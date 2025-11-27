package controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderItemDAO;
import dao.ProductDao;
import models.OrderItem;
import models.Products;

@WebServlet("/OrderItemController")
public class OrderItemController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String oid = request.getParameter("id");
    	int orderId = Integer.parseInt(oid);

        try {
            OrderItemDAO dao = new OrderItemDAO();
            ProductDao pdao = new ProductDao();
            
            List<OrderItem> items = dao.getItemsByOrderId(orderId);
            
            List<Products> p = new ArrayList<Products>();
            for (OrderItem orderItem : items) {
				Products pp = pdao.getProductById(orderItem.getProduct_id());
				p.add(pp);
			}
            
            request.setAttribute("pp", p);
            
//            Products product = pdao.getProductById(items);

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
