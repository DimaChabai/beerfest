package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.entity.TicketType;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.PlaceRepository;
import by.chabai.service.TicketService;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindAllReserved;
import by.chabai.specification.impl.FestSpecificationTicketFindAllGroupByType;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.chabai.constant.ColumnName.COL_SUM;
import static by.chabai.constant.ColumnName.COL_TICKET_TYPE;
import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_TICKET_JSP;


public class ToTicketCommand implements Command {

    @Override
    public String execute(SessionRequestContent content) {
        TicketService service = new TicketService();
        service.calculateTicketCount(content);
        return JSP_TICKET_JSP;
    }
}
