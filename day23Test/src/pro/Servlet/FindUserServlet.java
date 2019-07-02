package pro.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import pro.domain.User;
import pro.service.Impl.UserService;
import pro.service.intService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        intService service = new UserService();
        String s = service.FindAllbyJason();
        System.out.println(s);
        //将字符串响应给服务器
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(s);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
