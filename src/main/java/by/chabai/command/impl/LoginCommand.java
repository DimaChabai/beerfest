package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.service.LoginService;

public class LoginCommand implements Command {

    @Override
    public String execute(SessionRequestContent content) {
        LoginService service = new LoginService();
        return service.login(content);
    }
}
