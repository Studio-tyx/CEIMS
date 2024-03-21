package edu.njust.servlet;

import edu.njust.entity.Admin;
import edu.njust.entity.EverydayReport;
import edu.njust.entity.EverydayRespond;
import edu.njust.service.EverydayReportService;

import javax.crypto.MacSpi;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;

public class RespondServlet extends HttpServlet {

    public RespondServlet() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session=req.getSession();
        Object obj=session.getAttribute("admin");
        if(obj==null){
            System.out.println("admin is null!");
        }
        Admin admin=(Admin)obj;
        String id=new String(req.getParameter("ID").getBytes("iso-8859-1"),"utf-8");
        String time=new String(req.getParameter("time").getBytes("iso-8859-1"),"utf-8");
        String message=new String(req.getParameter("message").getBytes("iso-8859-1"),"utf-8");
        Timestamp sub=Timestamp.valueOf(time);
        EverydayRespond erp=new EverydayRespond(id,sub,admin.getID(),message);
        EverydayReportService everydayReportService=new EverydayReportService();
        if(everydayReportService.replyRemark(erp)){
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"提交成功！\");\n" +
                    "            document.location.href=\"../adminIndex.jsp\";\n" +
                    "        </script>");
        }else{
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"提交失败！\");\n" +
                    "            document.location.href=\"../adminIndex.jsp\";\n" +
                    "        </script>");
        }
    }
}
