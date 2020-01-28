package by.beerfest.service.impl;

import by.beerfest.entity.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.UserRepository;
import by.beerfest.service.RegistrationService;
import by.beerfest.service.ServiceException;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationUserFindByEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {

    private static Logger logger = LogManager.getLogger();
    public static final String DEFAULT_AVATAR = "undefined_user_avatar.png";
    private static UserRepository repository = UserRepository.getInstance();

    public boolean addUser(String email, String phoneNumber, String password) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setAvatar(DEFAULT_AVATAR);
        FestSpecification specification = new FestSpecificationUserFindByEmail(user.getEmail());
        List<User> result = null;
        try {
            result = repository.query(specification);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (result.isEmpty()) {
            try {
                repository.add(user);
            } catch (RepositoryException e) {
                logger.error(e);
                throw new ServiceException(e);
            }
            logger.info("Account created: " + user);
            return true;
        } else {
            return false;
        }
    }
}
