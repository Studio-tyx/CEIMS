package edu.njust.servlet;

import edu.njust.entity.Admin;
import edu.njust.entity.EverydayReport;
import edu.njust.entity.User;
import edu.njust.service.EverydayReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EveryServlet extends HttpServlet {
    public EveryServlet() {
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
        HttpSession session=req.getSession();
        User user=null;
        Object obj=session.getAttribute("user");
        if(obj!=null){
            user=(User)obj;
        }
        else{
            obj=session.getAttribute("admin");
            Admin admin=(Admin)obj;
            user=(User)admin;
        }
        double temperature=Double.parseDouble(req.getParameter("temperature"));
        String tel=new String(req.getParameter("tele").getBytes("iso-8859-1"),"utf-8");
        String prov=new String(req.getParameter("prov").getBytes("iso-8859-1"),"utf-8");
        String city=new String(req.getParameter("city").getBytes("iso-8859-1"),"utf-8");
        String area=new String(req.getParameter("area").getBytes("iso-8859-1"),"utf-8");
        String addr=new String(req.getParameter("addr").getBytes("iso-8859-1"),"utf-8");
        String address=prov+city+area+addr;
        String msg=new String(req.getParameter("message").getBytes("iso-8859-1"),"utf-8");
        System.out.println(addr+","+msg);
        EverydayReport er=new EverydayReport(user.getID(),tel,address,temperature,msg);
        EverydayReportService everydayReportService=new EverydayReportService();
        if(everydayReportService.submitEveryReport(er)){
            //提交每日申报成功
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"提交成功！\");\n" +
                    "            document.location.href=\"../userIndex.jsp\";\n" +
                    "        </script>");
        }else{
            //提交每日申报失败
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"提交失败！\");\n" +
                    "            document.location.href=\"../userIndex.jsp\";\n" +
                    "        </script>");
        }
    }
}
