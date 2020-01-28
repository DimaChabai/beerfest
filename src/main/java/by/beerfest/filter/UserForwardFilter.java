package by.beerfest.filter;


import by.beerfest.constant.PageParameter;
import by.beerfest.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = {"/jsp/user/*"})
public class UserForwardFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        UserRole role = (UserRole) session.getAttribute(PageParameter.ROLE_NAME);
        if (!(UserRole.USER.equals(role) || UserRole.ADMIN.equals(role))) {
            RequestDispatcher dispatcher;
            if (role != null) {
                dispatcher = servletRequest.getServletContext().getRequestDispatcher("/jsp/main.jsp");
            } else {
                dispatcher = servletRequest.getServletContext().getRequestDispatcher("/index.jsp");
            }
            dispatcher.forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
