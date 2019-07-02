package pro.Servlet;

import org.apache.commons.beanutils.BeanUtils;
import pro.domain.User;
import pro.service.Impl.UserService;
import pro.service.intService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过request获得客户端发送的参数，然后将参数包装成对象
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        intService service = new UserService();
        System.out.println(map);
        User user = new User();
        try {
            BeanUtils.populate(user,map);
            int row =service.AddUser(user);
            if(row>0){
             //   "result"这里必须加双引号不然识别不了
                response.getWriter().write("{\"result\":true}");
            }
            else
            {
                response.getWriter().write("{\"result\":false}");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
