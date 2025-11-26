package controllers;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Users;

@WebFilter({"/*"})
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
        
        String path = req.getRequestURI().substring(req.getContextPath().length());
        
        HttpSession session = req.getSession(false);
        Users user = null;
        if (session != null) {
            user = (Users) session.getAttribute("user");
        }

        // chặn các role ko phải admin
        
        if (path.startsWith("/admin") || path.startsWith("/statistic")) {
            if (user == null || !"admin".equals(user.getRole())) {
                res.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }
        
        // trang nhân viên
        if (path.startsWith("/staff") || path.startsWith("/nhanvien")) {
            if (user == null || !( "staff".equals(user.getRole()) || "admin".equals(user.getRole()) )) {
                res.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }
        
        if(path.startsWith("/users") || path.startsWith("/thongtin")) {
        	if(user == null) {
        		res.sendRedirect(req.getContextPath() + "/login");
                return;
        	}
        }

        // xử lý bình thường
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    }
}
