package by.beerfest.filter;

import by.beerfest.constant.PageParameter;
import by.beerfest.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = {"/jsp/admin/*"})
public class AdminForwardFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        UserRole role = (UserRole) session.getAttribute(PageParameter.ROLE_NAME);
        if (!UserRole.ADMIN.equals(role)) {
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

    @Override
    public void destroy() {

    }
}
