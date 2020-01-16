package by.chabai.servlet;

import by.chabai.entity.Place;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/participant")
public class ParticipantController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Place> places = new ArrayList<>();

        request.setAttribute("places",places);
        getServletContext().getRequestDispatcher("/jsp/participant.jsp").forward(request,response);
    }
}
