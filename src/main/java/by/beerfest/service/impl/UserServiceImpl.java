package by.beerfest.service.impl;

import by.beerfest.entity.UserRole;
import by.beerfest.entity.impl.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.impl.UserRepository;
import by.beerfest.service.ServiceException;
import by.beerfest.service.UserService;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationUserFindByEmail;
import by.beerfest.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.beerfest.constant.ColumnName.*;

public class UserServiceImpl implements UserService {

    private static final UserRepository repository = UserRepository.getInstance();
    private static final String DEFAULT_AVATAR = "undefined_user_avatar.png";
    private static Logger logger = LogManager.getLogger();

    public void buildUser(ResultSet resultSet, User user) throws SQLException {
        user.setPassword(resultSet.getString(COL_PASSWORD));
        user.setPhoneNumber(resultSet.getString(COL_PHONE_NUMBER));
        user.setEmail(resultSet.getString(COL_EMAIL));
        user.setId(resultSet.getLong(COL_ID_USER));
        user.setRole(UserRole.valueOf(resultSet.getString(COL_ROLE_NAME)));
        user.setAvatar(resultSet.getString(COL_AVATAR));
    }

    public User authenticate(String email, String password) throws ServiceException {
        UserDataValidator validator = new UserDataValidator();
        if (!validator.emailValidate(email) || !validator.passwordValidate(password)) {
            return null;
        }
        FestSpecification specification = new FestSpecificationUserFindByEmail(email);
        List<User> result;
        try {
            result = repository.query(specification);
        } catch (RepositoryException e) {
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

    public boolean createAndAddUser(String email, String phoneNumber, String password) throws ServiceException {
        UserDataValidator validator = new UserDataValidator();
        if (!validator.emailValidate(email)
                || !validator.passwordValidate(password)
                || !validator.phoneNumberValidate(phoneNumber)) {
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
