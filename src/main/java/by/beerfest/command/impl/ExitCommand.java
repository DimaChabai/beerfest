package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.ID;
import static by.beerfest.constant.PagePath.ROOT_PAGE;

public class ExitCommand implements Command {

    private Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        logger.info("User with id(" + content.getSessionAttribute(ID) + ") logged out");
        content.invalidateSession();
        return ROOT_PAGE;
    }
}
