package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.User;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationUserFindByEmail;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_REGISTRATION_JSP;

public class RegistrationCommand implements Command {

    public static final String DEFAULT_AVATAR = "undefined_user_avatar.png";
    private static UserRepository repository = UserRepository.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(EMAIL));
        user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
        user.setPassword(request.getParameter(PASSWORD));
        user.setAvatar(DEFAULT_AVATAR);
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationUserFindByEmail(connection, user.getEmail());
        List<User> result = repository.query(specification);
        connectionPool.releaseConnection(connection);
        if (result.isEmpty()) {
            repository.add(user);
            //@todo
            request.setAttribute(MESSAGE, "Регистрация прошла успешно");
        } else {
            request.setAttribute(ERROR_MESSAGE, "Почта занята");
        }


        return JSP_REGISTRATION_JSP;
    }
}
