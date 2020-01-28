package by.beerfest.servlet;

import by.beerfest.constant.PageParameter;
import by.beerfest.entity.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.UserRepository;
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

import static by.beerfest.constant.PageParameter.ERROR_MESSAGE;
import static by.beerfest.constant.PagePath.BEERFEST_PROFILE;
import static by.beerfest.constant.PagePath.JSP_PROFILE_JSP;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserUpdateController extends HttpServlet {

    private static Logger logger = LogManager.getLogger();
    public static final String EMPTY_STRING = "";
    public static final String DOT = ".";
    private static final String UPLOAD_DIR = "avatars";
    private static final UserRepository repository = UserRepository.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter(PageParameter.PHONE_NUMBER);
        String password = request.getParameter(PageParameter.PASSWORD);
        UserDataValidator validator = new UserDataValidator();

        if (validator.passwordValidate(password) || validator.phoneNumberValidate(phoneNumber)) {


            HttpSession session = request.getSession();
            String applicationDir = request.getServletContext().getRealPath(EMPTY_STRING);
            String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator;
            File fileSaveDir = new File(uploadFileDir);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            Part part = request.getPart(PageParameter.FILE);
            String path = part.getSubmittedFileName();
            FestSpecification specification = new FestSpecificationUserFindByEmail((String) session.getAttribute(PageParameter.EMAIL));
            User user = null;
            try {
                user = repository.query(specification).get(0);
            } catch (RepositoryException e) {
                logger.error(e);//@FIXME
            }
            if (!path.isBlank()) {
                String randFilename;
                String fileName;
                randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf(DOT));
                fileName = uploadFileDir + randFilename;
                part.write(fileName);
                session.setAttribute(PageParameter.AVATAR, randFilename);
                user.setAvatar(randFilename);
            }

            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);

            try {
                repository.update(user);
            } catch (RepositoryException e) {
                logger.error(e);
            }
            session.setAttribute(PageParameter.EMAIL, user.getEmail());
            session.setAttribute(PageParameter.ROLE_NAME, user.getRole());
            session.setAttribute(PageParameter.PHONE_NUMBER, user.getPhoneNumber());
            session.setAttribute(PageParameter.AVATAR, user.getAvatar());
        } else {
            request.setAttribute(ERROR_MESSAGE,"page.message.invalid_user_data");
        }
        response.sendRedirect(BEERFEST_PROFILE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(JSP_PROFILE_JSP).forward(request, response);
    }
}
