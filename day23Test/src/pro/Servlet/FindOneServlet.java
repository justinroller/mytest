package pro.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import pro.domain.User;
import pro.service.Impl.UserService;
import pro.service.intService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findOneServlet")
public class FindOneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        intService service = new UserService();
        User user = service.Find(id);
        //获得json的核心对象
        ObjectMapper mapper = new ObjectMapper();
        if (user != null) {
            response.setContentType("text/html;charset=utf-8");
            String s = mapper.writeValueAsString(user);
            response.getWriter().write(s);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
