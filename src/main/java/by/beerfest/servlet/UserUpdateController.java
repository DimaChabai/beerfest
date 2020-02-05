package by.beerfest.servlet;

import by.beerfest.constant.PageParameter;
import by.beerfest.entity.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.impl.UserRepository;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationUserFindByEmail;
import by.beerfest.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.BEERFEST_PROFILE;
import static by.beerfest.constant.PagePath.JSP_PROFILE_JSP;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserUpdateController extends HttpServlet {

    private static Logger logger = LogManager.getLogger();
    private static final String EMPTY_STRING = "";
    private static final String DOT = ".";
    private static final String UPLOAD_DIR = "avatars";
    private static final UserRepository repository = UserRepository.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter(PHONE_NUMBER);
        String password = request.getParameter(PASSWORD);
        UserDataValidator validator = new UserDataValidator();

        if ((!password.isBlank() && validator.passwordValidate(password))
                || (!phoneNumber.isBlank() && validator.phoneNumberValidate(phoneNumber))) {
            HttpSession session = request.getSession();
            String applicationDir = request.getServletContext().getRealPath(EMPTY_STRING);
            String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator;
            File fileSaveDir = new File(uploadFileDir);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            Part part = request.getPart(FILE);
            String path = part.getSubmittedFileName();
            String email = (String) session.getAttribute(EMAIL);
            FestSpecification specification = new FestSpecificationUserFindByEmail(email);
            User user = null;
            try {
                user = repository.query(specification).get(0);
            } catch (RepositoryException e) {
                logger.error(e);
                response.sendError(500);
                return;
            }
            if (!path.isBlank()) {
                String randFilename;
                String fileName;
                randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(DOT));
                fileName = uploadFileDir + randFilename;
                part.write(fileName);
                session.setAttribute(AVATAR, randFilename);
                user.setAvatar(randFilename);
            }

            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            try {
                repository.update(user);
                session.setAttribute(EMAIL, user.getEmail());
                session.setAttribute(ROLE_NAME, user.getRole());
                session.setAttribute(PHONE_NUMBER, user.getPhoneNumber());
                session.setAttribute(AVATAR, user.getAvatar());
                request.setAttribute(MESSAGE,"page.message.user_update_success");
            } catch (RepositoryException e) {
                logger.error(e);//@TODO message
                request.setAttribute(ERROR_MESSAGE, "page.message.user_update_error");
            }
        } else {
            request.setAttribute(ERROR_MESSAGE, "page.message.invalid_user_data");
        }
        getServletContext().getRequestDispatcher(JSP_PROFILE_JSP).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(JSP_PROFILE_JSP).forward(request, response);
    }
}
