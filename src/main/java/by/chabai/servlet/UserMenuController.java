package by.chabai.servlet;

import by.chabai.entity.User;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationUserFindByEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

import static by.chabai.constant.HelperSymbols.DOT;
import static by.chabai.constant.HelperSymbols.EMPTY_STRING;
import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.BEERFEST_PROFILE;
import static by.chabai.constant.PagePath.JSP_PROFILE_JSP;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserMenuController extends HttpServlet {

    private static final String UPLOAD_DIR = "avatars";
    private static final UserRepository repository = UserRepository.getInstance();
    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String applicationDir = request.getServletContext().getRealPath(EMPTY_STRING);
        String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        Part part = request.getPart(FILE);
        String path = part.getSubmittedFileName();
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationUserFindByEmail(connection, (String) session.getAttribute(EMAIL));
        User user = repository.query(specification).get(0);        // @TODO Нужно ли здесь обрабатывать результат запрос, если я знаю, что он будет валидным всегда.
        connectionPool.releaseConnection(connection);
        if (!path.isBlank()) {
            String randFilename;
            String fileName;
            randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(DOT));
            fileName = uploadFileDir + randFilename;
            part.write(fileName);
            session.setAttribute(AVATAR, randFilename);
            user.setAvatar(randFilename);
        }
        user.setPhoneNumber(request.getParameter(PHONE_NUMBER));
        user.setPassword(request.getParameter(PASSWORD));

        repository.update(user);
        session.setAttribute(EMAIL, user.getEmail());
        session.setAttribute(ROLE_NAME, user.getRole());
        session.setAttribute(PHONE_NUMBER, user.getPhoneNumber());
        session.setAttribute(AVATAR, user.getAvatar());
        response.sendRedirect(BEERFEST_PROFILE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(JSP_PROFILE_JSP).forward(request, response);
    }
}
