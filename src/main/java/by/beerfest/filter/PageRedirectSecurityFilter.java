package by.beerfest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = { "/jsp/*" },
        initParams = { @WebInitParam(name = "MAIN_PATH", value = "/index.jsp") })
public class PageRedirectSecurityFilter implements Filter {

    private String indexPath;

    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("MAIN_PATH");
    }
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);//todo всё равно заходит на страницу
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
