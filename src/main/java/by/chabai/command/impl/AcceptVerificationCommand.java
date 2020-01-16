package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Participant;
import by.chabai.entity.User;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.ParticipantRepository;
import by.chabai.repository.UserRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationParticipantFindById;
import by.chabai.specification.impl.FestSpecificationUserFindById;

import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;

import static by.chabai.constant.PageParameter.ID;
import static by.chabai.constant.PagePath.JSP_VERIFICATION_JSP;

public class AcceptVerificationCommand implements Command {

    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static ParticipantRepository participantRepository = ParticipantRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter(ID));
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationParticipantFindById(connection,id);
        Participant participant = participantRepository.query(specification).get(0);//@TODO
        participant.setConfirmed(true);
        participantRepository.update(participant);
        return JSP_VERIFICATION_JSP;
    }
}
