package by.beerfest.command;

import by.beerfest.command.impl.*;

import static by.beerfest.constant.PagePath.*;

public enum CommandType {
    MAIN {{
        command = (v)->JSP_MAIN_JSP;
    }},
    REGISTRATION {{
        command = new RegistrationCommand();
    }},
    CREATE {{
        command = new CreateCommand();
    }},
    PARTICIPANT {{
        command = new ParticipantCommand();
    }},
    TICKET {{
        command = new TicketCommand();
    }},
    VERIFICATION {{
        command = new AcceptVerificationCommand();
    }},
    LOGIN {{
        command = new LoginCommand();
    }},
    TO_REGISTRATION {{
        command = (v) -> JSP_REGISTRATION_JSP;
    }},
    TO_LOGIN {{
        command = (v) -> JSP_LOGIN_JSP;
    }},
    TO_TICKET {{
        command = new ToTicketCommand();
    }},
    TO_BECOME_PARTICIPANT {{
        command = new ToBecomeParticipantCommand();
    }},
    TO_PROFILE {{
        command = (v) -> JSP_PROFILE_JSP;
    }},
    TO_CREATE {{
        command = (v) -> JSP_CREATE_JSP;
    }},
    TO_VERIFICATION {{
        command = new ToVerificationCommand();
    }},
    DECLINE_VERIFICATION {{
        command = new DeclineVerificationCommand();
    }},
    ACCEPT_VERIFICATION {{
        command = new AcceptVerificationCommand();
    }},
    EXIT {{
        command = new ExitCommand();
    }},
    CHANGE_LANGUAGE {{
        command = new ChangeLanguageCommand();
    }},
    TO_PARTICIPANT_LIST{{
        command = new ToParticipantListCommand();
    }};


    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
