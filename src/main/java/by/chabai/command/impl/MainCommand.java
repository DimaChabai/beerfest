package by.chabai.command.impl;

import by.chabai.command.Command;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PagePath.JSP_MAIN_JSP;

public class MainCommand implements Command {



    @Override
    public String execute(HttpServletRequest request) {
        return JSP_MAIN_JSP;
    }
}
