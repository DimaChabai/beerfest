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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.beerfest.repository.ColumnName.*;

public class UserServiceImpl implements UserService {

    private static final UserRepository repository = UserRepository.getInstance();
    public static final String MAIL = "mail";
    private static final String DEFAULT_AVATAR = "undefined_user_avatar.png";
    public static final String FROM = "from";
    private static final MailService mailService = new MailService();
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static Logger logger = LogManager.getLogger();

    public void buildUser(ResultSet resultSet, User user) throws SQLException {
        user.setPassword(resultSet.getString(COL_PASSWORD));
        user.setPhoneNumber(resultSet.getString(COL_PHONE_NUMBER));
        user.setEmail(resultSet.getString(COL_EMAIL));
        user.setId(resultSet.getLong(COL_ID_USER));
        user.setRole(UserRole.valueOf(resultSet.getString(COL_ROLE_NAME)));
        user.setAvatar(resultSet.getString(COL_AVATAR));
    }

    public Optional<User> authenticate(String email, String password) throws ServiceException {
        UserDataValidator validator = new UserDataValidator();
        if (!validator.emailValidate(email) || !validator.passwordValidate(password)) {
            return Optional.empty();
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
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
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

    public void sendMessageOnEmail(String email, String messageKey) throws ServiceException {
        FestSpecification specification = new FestSpecificationUserFindByEmail(email);
        try {
            User user = repository.query(specification).get(0);
            String hash = user.getPassword();
            String pass = new String(decoder.decode(hash.getBytes()));
            ResourceBundle mail = ResourceBundle.getBundle(MAIL);
            String from = mail.getString(FROM);
            Session session = mailService.getSession();

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setText(mail.getString(messageKey) + " " + pass);
            Transport.send(message);
            logger.info("Passwod send on " + email);
        } catch (MessagingException | RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
