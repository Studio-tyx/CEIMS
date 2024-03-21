package edu.njust.servlet;

import edu.njust.entity.Admin;
import edu.njust.entity.OutReport;
import edu.njust.entity.Tour;
import edu.njust.entity.User;
import edu.njust.service.OutBackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class OutInServlet extends HttpServlet {
    public OutInServlet() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
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
        String id=user.getID();
        Date outDate=Date.valueOf(req.getParameter("outTime").substring(0,10));
        Date plannedBack=Date.valueOf(req.getParameter("plannedBack").substring(0,10));
        String prov=new String(req.getParameter("prov").getBytes("iso-8859-1"),"utf-8");
        String city=new String(req.getParameter("city").getBytes("iso-8859-1"),"utf-8");
        String area=new String(req.getParameter("area").getBytes("iso-8859-1"),"utf-8");
        String addr=new String(req.getParameter("addr").getBytes("iso-8859-1"),"utf-8");
        String address=prov+city+area+addr;
        OutReport or=new OutReport(id,outDate,plannedBack,address);
        System.out.println(or.show());
        OutBackService outBackService=new OutBackService();
        if(outBackService.submitOutReport(or)){
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"提交成功！\");\n" +
                    "            document.location.href=\"../userIndex.jsp\";\n" +
                    "        </script>");
        }else{
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"提交失败！\");\n" +
                    "            document.location.href=\"../userIndex.jsp\";\n" +
                    "        </script>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out=resp.getWriter();
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
        Enumeration paramNames = req.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            System.out.print(paramName + "   ");
            String paramValue = req.getParameter(paramName);
            System.out.println( paramValue +"\n");
        }
        OutBackService outBackService=new OutBackService();
        String actualBack=req.getParameter("backDate");
        Date actualBackDay=Date.valueOf(actualBack);
        int outID=outBackService.getOutID(user.getID());
        String p="prov";
        String prov,area,city,addr,time;
        int count=1;
        Tour tour=null;
        List<Tour> tours = new ArrayList<Tour>();
        while(req.getParameter(p+count)!=null){
            prov= req.getParameter(p+count);
            area=req.getParameter("area"+count);
            city=req.getParameter("city"+count);
            addr=req.getParameter("addr"+count);
            time=req.getParameter("time"+count);
            Timestamp timestamp=Timestamp.valueOf(time);
            tour=new Tour(outID,timestamp,prov,city,area,addr);
            tours.add(tour);
            count++;
        }
       if( outBackService.submitBackReport(user.getID(),actualBackDay,tours)){
           out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                   "            alert(\"提交成功！\");\n" +
                   "            document.location.href=\"../userIndex.jsp\";\n" +
                   "        </script>");
       }else{
           out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                   "            alert(\"提交失败！\");\n" +
                   "            document.location.href=\"../userIndex.jsp\";\n" +
                   "        </script>");
       }

    }
}
