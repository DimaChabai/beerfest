package by.chabai.command.client;

import by.chabai.command.Command;
import by.chabai.command.impl.*;

import static by.chabai.constant.PagePath.*;

public enum CommandEnum {
    MAIN {{
        command = new MainCommand();//@TODO зачём двойные скобки бля
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
    TOREGISTRATION {{
        command = (v) -> JSP_REGISTRATION_JSP;
    }},
    TOLOGIN {{
        command = (v) -> JSP_LOGIN_JSP;
    }},
    TOTICKET {{
        command = new ToTicketCommand();
    }},
    TOPARTICIPANT {{
        command = new ToParticipantCommand();
    }},
    TOPROFILE {{
        command = (v) -> JSP_PROFILE_JSP;
    }},
    TOCREATE {{
        command = (v) -> JSP_CREATE_JSP;
    }},
    TOVERIFICATION {{
        command = new ToVerificationCommand();
    }},
    DECLINEVERIFICATION{{
        command = new DeclineVerificationCommand();
    }},
    ACCEPTVERIFICATION{{
        command = new AcceptVerificationCommand();
    }};


    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
