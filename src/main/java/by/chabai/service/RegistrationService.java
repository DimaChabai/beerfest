package by.chabai.service;

import by.chabai.content.SessionRequestContent;
import by.chabai.entity.User;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationUserFindByEmail;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.chabai.constant.PageParameter.*;

public class RegistrationService {

    public static final String DEFAULT_AVATAR = "undefined_user_avatar.png";
    private static UserRepository repository = UserRepository.getInstance();

    public void addUser(SessionRequestContent content){
        User user = new User();
        user.setEmail(content.getRequestParameter(EMAIL));
        user.setPhoneNumber(content.getRequestParameter(PHONE_NUMBER));
        user.setPassword(content.getRequestParameter(PASSWORD));
        user.setAvatar(DEFAULT_AVATAR);
        FestSpecification specification = new FestSpecificationUserFindByEmail(user.getEmail());
        List<User> result = repository.query(specification);
        if (result.isEmpty()) {
            repository.add(user);
            content.setRequestAttribute(MESSAGE, "Регистрация прошла успешно");
        } else {
            content.setRequestAttribute(ERROR_MESSAGE, "Почта занята");
        }
    }
}
