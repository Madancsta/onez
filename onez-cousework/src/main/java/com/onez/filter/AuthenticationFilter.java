package com.onez.filter;

import java.io.IOException;

import com.onez.util.CookieUtil;
import com.onez.util.SessionUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String ctx = req.getContextPath();   // usually "" on cloud
        String path = uri.substring(ctx.length());  // normalized path

        // ------------------------------------------------------------
        // 1. ALLOW STATIC RESOURCES
        // ------------------------------------------------------------
        if (path.startsWith("/css") ||
            path.startsWith("/js") ||
            path.startsWith("/images") ||
            path.startsWith("/resources") ||
            path.startsWith("/WEB-INF") ||    // required for JSP internal forwards
            path.matches(".*\\.(png|jpg|jpeg|svg|css|js|ico)$")) {

            chain.doFilter(request, response);
            return;
        }

        // ------------------------------------------------------------
        // 2. ALLOW HOME ROOT REQUESTS
        // ------------------------------------------------------------
        if (path.equals("") ||     // example: contextPath = ""
            path.equals("/") ||
            path.equals("/home")) {

            chain.doFilter(request, response);
            return;
        }

        // ------------------------------------------------------------
        // 3. ALWAYS ALLOW LOGOUT
        // ------------------------------------------------------------
        if (path.equals("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        // ------------------------------------------------------------
        // 4. CHECK LOGIN + ROLE
        // ------------------------------------------------------------
        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;

        String role = CookieUtil.getCookie(req, "role") != null
                ? CookieUtil.getCookie(req, "role").getValue()
                : null;

        // ------------------------------------------------------------
        // 5. PUBLIC PAGES
        // ------------------------------------------------------------
        if (path.equals("/login") ||
            path.equals("/register") ||
            path.equals("/aboutUs") ||
            path.equals("/contact") ||
            path.equals("/privacy") ||
            path.equals("/return") ||
            path.equals("/terms") ||
            path.equals("/warranty") ||
            path.startsWith("/search") ||
            path.startsWith("/viewCategory") ||
            path.startsWith("/viewProduct")) {

            chain.doFilter(request, response);
            return;
        }

        // ------------------------------------------------------------
        // 6. NOT LOGGED IN → redirect to login
        // ------------------------------------------------------------
        if (!isLoggedIn) {
            res.sendRedirect(ctx + "/login");
            return;
        }

        // ------------------------------------------------------------
        // 7. ADMIN ACCESS RULES
        // ------------------------------------------------------------
        if ("admin".equals(role)) {
            if (path.startsWith("/adminDashboard") ||
                path.startsWith("/modifyUsers") ||
                path.startsWith("/products") ||
                path.startsWith("/admin/orders")) {

                chain.doFilter(request, response);
                return;
            } else {
                res.sendRedirect(ctx + "/adminDashboard");
                return;
            }
        }

        // ------------------------------------------------------------
        // 8. CUSTOMER ACCESS RULES
        // ------------------------------------------------------------
        if ("customer".equals(role)) {
            if (path.startsWith("/userDashboard") ||
                path.startsWith("/order") ||
                path.startsWith("/order-history") ||
                path.startsWith("/processOrder") ||
                path.startsWith("/cart") ||
                path.startsWith("/wishlist") ||
                path.startsWith("/orderHistory")) {

                chain.doFilter(request, response);
                return;
            } else {
                res.sendRedirect(ctx + "/home");
                return;
            }
        }

        // ------------------------------------------------------------
        // 9. DEFAULT → allow
        // ------------------------------------------------------------
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
