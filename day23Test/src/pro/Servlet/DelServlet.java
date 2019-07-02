package pro.Servlet;

import pro.service.Impl.UserService;
import pro.service.intService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delServlet")
public class DelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        intService service  = new UserService();
        int row = service.DelUser(id);
        if(row>0){
            response.getWriter().write("{\"result\":true}");
        }
            else {
            response.getWriter().write("{\"result\":false}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
