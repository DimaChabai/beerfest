
package by.beerfest.filter;

import by.beerfest.command.CommandType;
import by.beerfest.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.beerfest.constant.PageParameter.COMMAND;
import static by.beerfest.constant.PageParameter.ROLE_NAME;

@WebFilter(urlPatterns = "/controller",
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/")})
public class PermissionFilter implements Filter {
    public static final String INDEX_PATH = "INDEX_PATH";
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter(INDEX_PATH);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        CommandType command;
        String commandName = servletRequest.getParameter(COMMAND);
        if (commandName == null || commandName.isBlank()) {
            servletRequest.getServletContext().getRequestDispatcher(indexPath).forward(servletRequest, servletResponse);
            return;
        }
        command = CommandType.valueOf(commandName.toUpperCase());
        UserRole role = (UserRole) session.getAttribute(ROLE_NAME);
        if (role == null) {
            if (!(CommandType.TO_LOGIN.equals(command)
                    || CommandType.LOGIN.equals(command)
                    || CommandType.TO_REGISTRATION.equals(command)
                    || CommandType.REGISTRATION.equals(command)
                    || CommandType.CHANGE_LOCALE.equals(command))) {
                command = CommandType.MAIN;
            }
        } else {
            if ((CommandType.TICKET.equals(command) || CommandType.BECOME_PARTICIPANT.equals(command))
                    && !(UserRole.USER.equals(role) || UserRole.ADMIN.equals(role))) {
                command = CommandType.MAIN;
            } else if ((CommandType.CREATE.equals(command) || CommandType.VERIFICATION.equals(command))
                    && !UserRole.ADMIN.equals(role)) {
                command = CommandType.MAIN;
            } else if (CommandType.TO_LOGIN.equals(command) || CommandType.LOGIN.equals(command)) {
                command = CommandType.MAIN;
            }
        }
        servletRequest.setAttribute(COMMAND, command.toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

