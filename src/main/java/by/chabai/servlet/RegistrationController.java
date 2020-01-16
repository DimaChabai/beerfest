package by.chabai.servlet;

import by.chabai.entity.User;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationUserFindByEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import static by.chabai.constant.PageParameter.*;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private static UserRepository repository = UserRepository.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter(EMAIL));
        user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
        user.setPassword(request.getParameter(PASSWORD));
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationUserFindByEmail(connection, user.getEmail());

        List<User> result = repository.query(specification);
        connectionPool.releaseConnection(connection);
        if (result.isEmpty()) {
            repository.add(user);
            request.setAttribute(MESSAGE, "Регистрация прошла успешно");
        } else {
            request.setAttribute(ERROR_MESSAGE, "Почта занята");
        }

        ConnectionPool.INSTANCE.releaseConnection(connection);
        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }
}
