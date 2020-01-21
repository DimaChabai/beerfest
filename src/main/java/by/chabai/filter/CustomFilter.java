
package by.chabai.filter;

import by.chabai.command.CommandType;
import by.chabai.constant.PageParameter;
import by.chabai.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index")
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
        CommandType command;
        String commandName = servletRequest.getParameter(PageParameter.COMMAND);
        if (commandName == null) {
            commandName = "INDEX";
        }
        command = CommandType.valueOf(commandName.toUpperCase());//@TODO Может разрешения как то в командах хранить?
        UserRole role = (UserRole) session.getAttribute(PageParameter.ROLE_NAME);

        if (role == null) {
            if (!(command.equals(CommandType.TO_LOGIN)
                    || command.equals(CommandType.LOGIN)
                    || command.equals(CommandType.TO_REGISTRATION)
                    || command.equals(CommandType.REGISTRATION))) {
                command = CommandType.INDEX;
            }
        } else {
            if ((command.equals(CommandType.TO_TICKET) || command.equals(CommandType.TO_PARTICIPANT))
                    && !(role.equals(UserRole.USER) || role.equals(UserRole.ADMIN))) {
                command = CommandType.MAIN;
            } else if ((command.equals(CommandType.CREATE) || command.equals(CommandType.VERIFICATION))
                    && !role.equals(UserRole.ADMIN)) {
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

