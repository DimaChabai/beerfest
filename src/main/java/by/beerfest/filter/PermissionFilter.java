
package by.beerfest.filter;

import by.beerfest.command.CommandType;
import by.beerfest.constant.PageParameter;
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
        initParams = {@WebInitParam(name = "MAIN_COMMAND", value = "MAIN")})
public class PermissionFilter implements Filter {
    private String MAIN;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MAIN = filterConfig.getInitParameter("MAIN_COMMAND");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //@TODO вместо замены команды делать форвард?
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        CommandType command;
        String commandName = servletRequest.getParameter(COMMAND);
        if (commandName == null || commandName.isBlank()) {
            commandName = MAIN;
        }
        command = CommandType.valueOf(commandName.toUpperCase());
        UserRole role = (UserRole) session.getAttribute(ROLE_NAME);
        if (role == null) {
            if (!(CommandType.TO_LOGIN.equals(command)
                    || CommandType.LOGIN.equals(command)
                    || CommandType.TO_REGISTRATION.equals(command)
                    || CommandType.REGISTRATION.equals(command)
                    || CommandType.CHANGE_LANGUAGE.equals(command))) {
                command = CommandType.MAIN;
            }
        } else {
            if ((CommandType.TICKET.equals(command) || CommandType.PARTICIPANT.equals(command))
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

