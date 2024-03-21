package edu.njust.servlet;

import edu.njust.entity.User;
import edu.njust.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserModifyServlet extends HttpServlet {
    public UserModifyServlet() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        UserService userService = new UserService();
        String ID = new String(req.getParameter("ID").getBytes("iso-8859-1"), "utf-8");
        String pswd = new String(req.getParameter("pswd").getBytes("iso-8859-1"), "utf-8");
        String department = new String(req.getParameter("department").getBytes("iso-8859-1"), "utf-8");
        String name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        User user = new User(ID, pswd, name, department);
        if (userService.modifyUser(user)) {
            if (req.getParameter("demo3") != null) {//管理员
                userService.addAdmin(user);
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"修改成功！\");\n" +
                        "            document.location.href=\"../manageUser.jsp\";\n" +
                        "        </script>");
            } else if (req.getParameter("demo4") != null) {
                if (userService.isAdmin(user)) {
                    //本来就是管理员
                    userService.cancelAdmin(user);
                } else {
                    //那没事了
                }
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"修改成功！\");\n" +
                        "            document.location.href=\"../manageUser.jsp\";\n" +
                        "        </script>");
            }
        } else {
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"操作失败！\");\n" +
                    "            document.location.href=\"../manageUser.jsp\";\n" +
                    "        </script>");
        }
    }
}
