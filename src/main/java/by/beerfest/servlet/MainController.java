package by.beerfest.servlet;

import by.beerfest.command.Command;
import by.beerfest.command.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.beerfest.constant.PagePath.ROOT_PAGE;

/**
 * The main controller of the application. Defines a command and initializes its execution.
 * Receive and send data from request and session
 */
@WebServlet("/controller")
public class MainController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionRequestContent content = new SessionRequestContent();
        content.extractValues(request);
        Command command = CommandProvider.defineCommand(content);
        String page = command.execute(content);
        content.insertAttributes(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ROOT_PAGE;
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + page));
        }
    }
}
