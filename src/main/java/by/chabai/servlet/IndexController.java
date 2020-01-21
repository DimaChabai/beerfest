package by.chabai.servlet;

import by.chabai.command.Command;
import by.chabai.command.ActionFactory;
import by.chabai.content.SessionRequestContent;
import by.chabai.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.chabai.constant.PagePath.ROOT_PAGE;

@WebServlet("/index")
public class IndexController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        SessionRequestContent content = new SessionRequestContent();
        content.extractValues(request);
        Command command = ActionFactory.defineCommand(content);

        page = command.execute(content);

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
