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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import static by.chabai.constant.PageParameter.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final UserRepository repository = UserRepository.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(EMAIL);
        Connection connection = pool.getConnection();
        FestSpecification specification = new FestSpecificationUserFindByEmail(connection, email);
        List<User> result = repository.query(specification);
        pool.releaseConnection(connection);
        String password = request.getParameter(PASSWORD);
        if (result.size() == 1) {
            User user = result.get(0);
            if (password.equals(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("email",user.getEmail());
                session.setAttribute("roleid",user.getRole());
                session.setAttribute("phone_number",user.getPhoneNumber());
                session.setAttribute("phone_number",user.getPhoneNumber());
                response.sendRedirect("/beerfest/main");
                return;
            } else {
                request.setAttribute(ERROR_MESSAGE, "Неправильный пароль");
            }
        } else {
            request.setAttribute(ERROR_MESSAGE, "Неправильный логин");
        }
        getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}