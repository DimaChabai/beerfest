
package by.beerfest.filter;

import by.beerfest.command.CommandType;
import by.beerfest.constant.PageParameter;
import by.beerfest.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index")
public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        CommandType command;
        String commandName = servletRequest.getParameter(PageParameter.COMMAND);
        if (commandName == null) {
            commandName = "INDEX";
        }
        command = CommandType.valueOf(commandName.toUpperCase());
        UserRole role = (UserRole) session.getAttribute(PageParameter.ROLE_NAME);
//@TODO стоит ли разбить на фильтры, ведь тут я команды проверяю
        if (role == null) {
            if (!(CommandType.TO_LOGIN.equals(command)
                    || CommandType.LOGIN.equals(command)
                    || CommandType.TO_REGISTRATION.equals(command)
                    || CommandType.REGISTRATION.equals(command)
                    || CommandType.CHANGE_LANGUAGE.equals(command))) {
                command = CommandType.INDEX;
            }//@TODO документация про переносы
        } else {
            if ((CommandType.TICKET.equals(command) || CommandType.PARTICIPANT.equals(command))
                    && !(UserRole.USER.equals(role) || UserRole.ADMIN.equals(role))) {
                command = CommandType.MAIN;
            } else if ((CommandType.CREATE.equals(command)
                    || CommandType.VERIFICATION.equals(command))
                    && !UserRole.ADMIN.equals(role)) {
                command = CommandType.MAIN;
            }
        }
        servletRequest.setAttribute(PageParameter.COMMAND, command.toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

