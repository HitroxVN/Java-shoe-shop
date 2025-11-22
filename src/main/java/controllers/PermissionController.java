package controllers;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Users;

@WebFilter({"/admin/*", "/AdminController"})
public class PermissionController implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	// TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

    	HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        Users user = null;
        if (session != null) {
            user = (Users) session.getAttribute("user");
        }

        // chặn các role ko phải admin
        if (user == null || !"admin".equals(user.getRole())) {
        	res.sendRedirect(req.getContextPath() + "/home"); // về controller home
            return;
        }

        // xử lý bình thường
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    }
}
