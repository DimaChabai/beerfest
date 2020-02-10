package by.beerfest.command;

import by.beerfest.command.impl.*;
import by.beerfest.entity.UserRole;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static by.beerfest.constant.PagePath.*;
import static by.beerfest.entity.UserRole.*;

/**
 * Contain objects that contain command and roles that can execute them.
 */
public enum CommandType {
    MAIN(v -> JSP_MAIN_JSP, UserRole.values()),
    REGISTRATION(new RegistrationCommand(), UNAUTHORIZED),
    CREATE_PLACE(new CreatePlaceCommand(), ADMIN),
    BECOME_PARTICIPANT(new BecomeParticipantCommand(), USER),
    TICKET_BOOK(new TicketBookCommand(), USER),
    VERIFICATION(new AcceptVerificationCommand(), ADMIN),
    LOGIN(new LoginCommand(), UNAUTHORIZED),
    TO_REGISTRATION(v -> JSP_REGISTRATION_JSP, UNAUTHORIZED),
    TO_LOGIN(v -> JSP_LOGIN_JSP, UNAUTHORIZED),
    TO_TICKET_BOOK(new ToTicketBookCommand(), USER),
    TO_BECOME_PARTICIPANT(new ToBecomeParticipantCommand(), USER),
    TO_PROFILE(v -> JSP_PROFILE_JSP, UserRole.values()),
    TO_CREATE_PLACE(v -> JSP_CREATE_JSP, ADMIN),
    TO_VERIFICATION(new ToVerificationCommand(), ADMIN),
    DECLINE_VERIFICATION(new DeclineVerificationCommand(), ADMIN),
    ACCEPT_VERIFICATION(new AcceptVerificationCommand(), ADMIN),
    EXIT(new ExitCommand(), UserRole.values()),
    CHANGE_LOCALE(new ChangeLocaleCommand(), UserRole.values()),
    TO_PARTICIPANT_LIST(new ToParticipantListCommand(), ADMIN, USER, PARTICIPANT, GUEST);

    Set<UserRole> allowedRole = new HashSet<>();

    Command command;

    CommandType(Command command, UserRole... roles) {
        this.command = command;
        allowedRole.addAll(Arrays.asList(roles));
    }

    public Command getCurrentCommand() {
        return command;
    }

    public boolean checkPermission(UserRole role) {
        return allowedRole.contains(role);
    }
}
