package by.beerfest.service.impl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.ResourceBundle;

import static by.beerfest.service.impl.UserServiceImpl.FROM;
import static by.beerfest.service.impl.UserServiceImpl.MAIL;

public class MailService {

    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";

    public Session getSession() {
        ResourceBundle mail = ResourceBundle.getBundle(MAIL);
        String from = mail.getString(FROM);
        Properties properties = new Properties();
        properties.put(MAIL_SMTP_HOST, mail.getString(MAIL_SMTP_HOST));
        properties.put(MAIL_SMTP_AUTH, mail.getString(MAIL_SMTP_AUTH));
        properties.put(MAIL_SMTP_SOCKET_FACTORY_PORT, mail.getString(MAIL_SMTP_SOCKET_FACTORY_PORT));
        properties.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, mail.getString(MAIL_SMTP_SOCKET_FACTORY_CLASS));
        return Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, mail.getString("password"));
                    }
                });
    }
}
