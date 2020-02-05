package by.beerfest.service.impl;

import by.beerfest.entity.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.impl.UserRepository;
import by.beerfest.service.RegistrationService;
import by.beerfest.service.ServiceException;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationUserFindByEmail;
import by.beerfest.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {

    private static Logger logger = LogManager.getLogger();
    private static final String DEFAULT_AVATAR = "undefined_user_avatar.png";
    private static UserRepository repository = UserRepository.getInstance();

    public boolean createAndAddUser(String email, String phoneNumber, String password) throws ServiceException {

        UserDataValidator validator = new UserDataValidator();

        if(!validator.emailValidate(email)
                || !validator.passwordValidate(password)
                || !validator.phoneNumberValidate(phoneNumber)){
            return false;
        }
        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setAvatar(DEFAULT_AVATAR);
        FestSpecification specification = new FestSpecificationUserFindByEmail(user.getEmail());
        List<User> result;
        try {
            result = repository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        if (result.isEmpty()) {
            try {
                int id = repository.add(user);
                user.setId(id);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
            logger.info("Account created: " + user);
            return true;
        } else {
            return false;
        }
    }
}
