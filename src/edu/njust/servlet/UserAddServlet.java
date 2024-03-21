package edu.njust.servlet;

import edu.njust.entity.User;
import edu.njust.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

public class UserAddServlet extends HttpServlet {
    public UserAddServlet() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String ID = req.getParameter("regist_user");
        String password = req.getParameter("regist_pwd");
        String name = req.getParameter("regist_name");
        String department = req.getParameter("regist_department");
        User user = new User(ID, password, name, department);
        //System.out.println(user.show());
        UserService userService = new UserService();
        if (userService.addUser(user)) {
            //最好再加一个“添加用户成功”的弹窗
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"注册成功！\");\n" +
                    "            document.location.href=\"../login.jsp\";\n" +
                    "        </script>");
        } else {
            //添加用户失败
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"注册失败！\");\n" +
                    "            document.location.href=\"../login.jsp\";\n" +
                    "        </script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        UserService userService = new UserService();

        //String ID = new String(req.getParameter("ID").getBytes("iso-8859-1"), "utf-8");
        String pswd = new String(req.getParameter("pswd").getBytes("iso-8859-1"), "utf-8");
        String department = new String(req.getParameter("department").getBytes("iso-8859-1"), "utf-8");
        //String name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        //System.out.println(department);
        //User user = new User(null, pswd, null, department);
        if (userService.batchModifyUser(department,pswd)) {
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"修改成功！\");\n" +
                    "            document.location.href=\"../manageUser.jsp\";\n" +
                    "        </script>");
        } else {
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"操作失败！\");\n" +
                    "            document.location.href=\"../manageUser.jsp\";\n" +
                    "        </script>");
        }
    }
}

