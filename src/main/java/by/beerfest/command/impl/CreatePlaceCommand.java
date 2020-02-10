package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.service.PlaceService;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.PlaceServiceImpl;
import by.beerfest.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageMessage.CREATE_PLACE_ERROR_MESSAGE;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_CREATE_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 * Uses @{code CreateServiceImpl} to create Place
 */
public class CreatePlaceCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private PlaceService service = new PlaceServiceImpl();

    /**
     * Gets place parameters from session to pass to the {@code CreateServiceImpl}
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        try {
            String placeType = content.getRequestParameter(PLACE_TYPE)[0];
            String seats = content.getRequestParameter(SEATS)[0];
            service.createPlace(placeType, seats);
        } catch (ServiceException e) {
            logger.error(e);
            content.setSessionAttribute(ERROR_MESSAGE, CREATE_PLACE_ERROR_MESSAGE);
        }
        return JSP_CREATE_JSP;
    }
}
