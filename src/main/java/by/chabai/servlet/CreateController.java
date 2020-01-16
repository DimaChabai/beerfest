package by.chabai.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/create")
public class CreateController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> types = new ArrayList<>();
        types.add("Маленький");
        types.add("Средний");
        types.add("Большой");
        request.setAttribute("types",types);
        getServletContext().getRequestDispatcher("/jsp/create.jsp").forward(request,response);
    }
}
