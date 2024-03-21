package edu.njust.servlet;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import edu.njust.entity.Tour;
import edu.njust.service.OutBackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TrackServlet extends HttpServlet {
    public TrackServlet() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //System.out.println("hey here is track!");
        HttpSession session=req.getSession();
        String id=req.getParameter("ID");
        //System.out.println("servlet:"+id);
        OutBackService outBackService=new OutBackService();
        List<Tour> tours=outBackService.getTour(id);
        session.setAttribute("tours",tours);
        resp.sendRedirect("../track.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String place=new String(req.getParameter("place").getBytes("iso-8859-1"),"utf-8");
        System.out.println("servlet:"+place);
        HttpSession session=req.getSession();
        session.setAttribute("place",place);
        resp.sendRedirect("../track.jsp");
    }
}
