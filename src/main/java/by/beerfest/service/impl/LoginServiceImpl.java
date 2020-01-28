package by.beerfest.service.impl;

import by.beerfest.entity.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.UserRepository;
import by.beerfest.service.LoginService;
import by.beerfest.service.ServiceException;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationUserFindByEmail;
import by.beerfest.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginServiceImpl implements LoginService {

    private static final UserRepository repository = UserRepository.getInstance();
    private static Logger logger = LogManager.getLogger();

    public User login(String email, String password) throws ServiceException {
        UserDataValidator validator = new UserDataValidator();
        if(!validator.emailValidate(email) || !validator.passwordValidate(password)){
            return null;
        }
        FestSpecification specification = new FestSpecificationUserFindByEmail(email);
        List<User> result;
        try {
            result = repository.query(specification);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (result.size() == 1) {
            User user = result.get(0);
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
