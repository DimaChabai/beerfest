package by.chabai.command;

import by.chabai.command.impl.*;

import static by.chabai.constant.PagePath.*;

public enum CommandType {
    INDEX {{
        command = (v) -> ROOT_PAGE;
    }},
    MAIN {{
        command = new MainCommand();
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
    TO_PARTICIPANT {{
        command = new ToParticipantCommand();
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
    EXIT{{
        command = new ExitCommand();
    }},
    CHANGE_LANGUAGE {{
        command = new ChangeLanguageCommand();
    }};


    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
