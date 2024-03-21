package edu.njust.servlet;

import edu.njust.entity.EpidemicNumber;
import edu.njust.service.NumberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Enumeration;

public class NumberServlet extends HttpServlet {
    public NumberServlet() {
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
        /*Enumeration paramNames = req.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            System.out.print(paramName + "   ");
            String paramValue = req.getParameter(paramName);
            System.out.println( paramValue +"\n");
        }*/
        String obj;
        obj=req.getParameter("XYQZ");int xyqz=Integer.parseInt(obj);
        obj=req.getParameter("WZZ");int wzz=Integer.parseInt(obj);
        obj=req.getParameter("XYYS");int xyys=Integer.parseInt(obj);
        obj=req.getParameter("XYZZ");int xyzz=Integer.parseInt(obj);
        obj=req.getParameter("LJQZ");int ljqz=Integer.parseInt(obj);
        obj=req.getParameter("JWSR");int jwsr=Integer.parseInt(obj);
        obj=req.getParameter("LJZY");int ljzy=Integer.parseInt(obj);
        obj=req.getParameter("LJSW");int ljsw=Integer.parseInt(obj);
        obj=req.getParameter("dXYQZ");int dxyqz=Integer.parseInt(obj);
        obj=req.getParameter("dWZZ");int dwzz=Integer.parseInt(obj);
        obj=req.getParameter("dXYYS");int dxyys=Integer.parseInt(obj);
        obj=req.getParameter("dXYZZ");int dxyzz=Integer.parseInt(obj);
        obj=req.getParameter("dLJQZ");int dljqz=Integer.parseInt(obj);
        obj=req.getParameter("dJWSR");int djwsr=Integer.parseInt(obj);
        obj=req.getParameter("dLJZY");int dljzy=Integer.parseInt(obj);
        obj=req.getParameter("dLJSW");int dljsw=Integer.parseInt(obj);
        int[] c={xyqz,wzz,xyys,xyzz,ljqz,jwsr,ljzy,ljsw};
        int[] d={dxyqz,dwzz,dxyys,dxyzz,dljqz,djwsr,dljzy,dljsw};
        EpidemicNumber campus=new EpidemicNumber(new Date(System.currentTimeMillis()),c,d);
        NumberService numberService=new NumberService();
        if(numberService.hasTodayCampus()){
            numberService.updateCampus(campus);
            out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                    "            alert(\"修改成功！\");\n" +
                    "            document.location.href=\"../adminIndex.jsp\";\n" +
                    "        </script>");
        }else {
            if (numberService.addCampus(campus)) {
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"提交成功！\");\n" +
                        "            document.location.href=\"../adminIndex.jsp\";\n" +
                        "        </script>");
            } else {
                out.print("<script type=\"text/javascript\" charset=\"UTF-8\">\n" +
                        "            alert(\"提交失败！\");\n" +
                        "            document.location.href=\"../adminIndex.jsp\";\n" +
                        "        </script>");
            }
        }
    }
}
