package by.beerfest.servlet;

import by.beerfest.command.ActionFactory;
import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.beerfest.constant.PagePath.ROOT_PAGE;

@WebServlet("/index")//@TODO Заменить на controller
public class MailController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionRequestContent content = new SessionRequestContent();

        content.extractValues(request);
        Command command = ActionFactory.defineCommand(content);

        String page = command.execute(content);

        content.insertAttributes(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ROOT_PAGE;
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
