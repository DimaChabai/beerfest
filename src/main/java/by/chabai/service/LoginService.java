package by.chabai.service;

import by.chabai.content.SessionRequestContent;
import by.chabai.entity.User;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationUserFindByEmail;

import java.util.List;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_LOGIN_JSP;
import static by.chabai.constant.PagePath.JSP_MAIN_JSP;

public class LoginService {

    private static final UserRepository repository = UserRepository.getInstance();


    public String login(SessionRequestContent content) {
        String page = JSP_LOGIN_JSP;
        String email = content.getRequestParameter(EMAIL);
        FestSpecification specification = new FestSpecificationUserFindByEmail(email);
        List<User> result = repository.query(specification);
        String password = content.getRequestParameter(PASSWORD);
        if (result.size() == 1) {
            User user = result.get(0);
            if (password.equals(user.getPassword())) {
                content.setSessionAttribute(ID, user.getId());
                content.setSessionAttribute(EMAIL, user.getEmail());
                content.setSessionAttribute(ROLE_NAME, user.getRole());
                content.setSessionAttribute(PHONE_NUMBER, user.getPhoneNumber());
                content.setSessionAttribute(AVATAR, user.getAvatar());
                content.setSessionAttribute(LANGUAGE, RU_RU);
                page = JSP_MAIN_JSP;            //@TODO
            } else {
                content.setRequestAttribute(ERROR_MESSAGE, "Неправильный пароль");
            }
        } else {
            content.setRequestAttribute(ERROR_MESSAGE, "Неправильный логин");
        }
        return page;
    }
}
