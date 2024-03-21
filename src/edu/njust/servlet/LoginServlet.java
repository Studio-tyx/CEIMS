package edu.njust.servlet;

import edu.njust.entity.Admin;
import edu.njust.entity.User;
import edu.njust.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        HttpSession session=req.getSession();
        String ID=req.getParameter("regist_user");
        String name=req.getParameter("regist_name");
        String department=req.getParameter("regist_department");
        String password=req.getParameter("regist_pwd");
        User user=new User(ID,password,name,department);
        UserService userService=new UserService();
        if(userService.existUser(user.getID())){
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"用户已存在！\");\n" +
                    "            document.location.href=\"../login.jsp\";\n" +
                    "        </script>");
        }
        else{
            if(userService.addUser(user)){
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"注册成功！\");\n" +
                        "            document.location.href=\"../login.jsp\";\n" +
                        "        </script>");
            }else{
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"注册失败！\");\n" +
                        "            document.location.href=\"../register.jsp\";\n" +
                        "        </script>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
        HttpSession session=req.getSession();
        String ID=req.getParameter("login_account");
        String password=req.getParameter("login_pwd");
        User user=new User(ID,password);
        UserService userService=new UserService();
        int failureCode=0;
        if(!userService.existUser(user.getID())){
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"用户不存在！\");\n" +
                    "            document.location.href=\"../login.jsp\";\n" +
                    "        </script>");
        }
        else{
            if(userService.login(user)){
                user=userService.findUser(ID);
                if(userService.isAdmin(user)) {//该用户为管理员
                    Admin admin=new Admin(user.getID(),user.getPassword(),user.getName(),user.getDepartment());
                    session.setAttribute("user",null);
                    session.setAttribute("admin",admin);
                    resp.sendRedirect("../adminIndex.jsp");
                }
                else{
                    session.setAttribute("user",user);
                    session.setAttribute("admin",null);
                    resp.sendRedirect("../userIndex.jsp");
                }
            }else{
                //账号密码不符合
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"密码错误！\");\n" +
                        "            document.location.href=\"../login.jsp\";\n" +
                        "        </script>");
            }
        }
    }
}
