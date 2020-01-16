package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Participant;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.ParticipantRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationParticipantFindByConfirmedIsFalse;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

import static by.chabai.constant.PageParameter.PARTICIPANTS;
import static by.chabai.constant.PagePath.JSP_VERIFICATION_JSP;

public class ToVerificationCommand implements Command {


    ParticipantRepository participantRepository = ParticipantRepository.getInstance();
    ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationParticipantFindByConfirmedIsFalse(connection);
        List<Participant> resultList = participantRepository.query(specification);
        connectionPool.releaseConnection(connection);
        request.setAttribute(PARTICIPANTS,resultList);
        return JSP_VERIFICATION_JSP;
    }
}
