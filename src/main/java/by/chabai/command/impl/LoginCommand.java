package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.User;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationUserFindByEmail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PageParameter.ERROR_MESSAGE;
import static by.chabai.constant.PagePath.JSP_LOGIN_JSP;
import static by.chabai.constant.PagePath.JSP_MAIN_JSP;

public class LoginCommand implements Command {

    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final UserRepository repository = UserRepository.getInstance();


    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationUserFindByEmail(connection, email);
        List<User> result = repository.query(specification);
        connectionPool.releaseConnection(connection);
        String password = request.getParameter(PASSWORD);
        if (result.size() == 1) {
            User user = result.get(0);
            if (password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute(ID,user.getId());
                session.setAttribute(EMAIL,user.getEmail());
                session.setAttribute(ROLE_NAME,user.getRole());
                session.setAttribute(PHONE_NUMBER,user.getPhoneNumber());
                session.setAttribute(AVATAR,user.getAvatar());
                return JSP_MAIN_JSP;
            } else {
                //@TODO
                request.setAttribute(ERROR_MESSAGE, "Неправильный пароль");
            }
        } else {
            request.setAttribute(ERROR_MESSAGE, "Неправильный логин");
        }
        return JSP_LOGIN_JSP;
    }
}
