package edu.njust.service;

import edu.njust.dao.OutReportDAO;
import edu.njust.dao.TourDAO;
import edu.njust.entity.OutReport;
import edu.njust.entity.Tour;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class OutBackService {
    /**
     * 提交出校申请：先判断上一次出校申请是否已销假，再增加出校申请记录
     * @param or OutReport servlet层封装出校申请信息（使用不包括OutReportID的构造器）
     * @return boolean 增加出校申请记录成败
     * @throws IOException 数据库读写错误
     */
    public boolean submitOutReport(OutReport or) throws IOException
    {
        boolean res=false;
        if(or!=null) {
            OutReportDAO outReportDAO = new OutReportDAO();
            if (!outReportDAO.existUser(or.getId())) {
                res = outReportDAO.addOutReport(or);
            } else {
                if (!outReportDAO.isOut(outReportDAO.getLastOutReportID(or.getId()))) {//学生在校内=上一次出校申请已销假
                    res = outReportDAO.addOutReport(or);
                }
            }
            System.out.println("OutBackService.submitOutReport:Have" + (res ? " " : " not ") + "add out report:" + or.show());
        }
        return res;
    }

    /**
     * 某用户是否未销假（已提交出校申请但是未销假）
     * @param id String 用户ID
     * @return boolean 用户是否未销假（仍在校外）
     * @throws IOException 数据库读写错误
     */
    public boolean isOut(String id) throws IOException
    {
        boolean res=false;
        if(id!=null){
            OutReportDAO outReportDAO=new OutReportDAO();
            res=outReportDAO.isOut(outReportDAO.getLastOutReportID(id));
        }
        System.out.println("OutBackService.isOut:user id:"+id+" is"+(res?" ":" not ")+"out");
        return res;
    }

    /**
     * OutReport表单是否存在某用户
     * @param id String 用户ID
     * @return boolean 是否存在某用户
     * @throws IOException 数据库读写错误
     */
    public boolean existUser(String id) throws IOException {
        boolean res=new OutReportDAO().existUser(id);
        System.out.println("OutBackService.existUser:"+(res?" ":" not ")+"exist id:"+id);
        return res;
    }

    /**
     * 通过用户ID返回出校申请ID
     * @param id String 用户ID
     * @return int OutReportID
     * @throws IOException 数据库读写错误
     */
    public int getOutID(String id) throws IOException {
        int res=new OutReportDAO().getLastOutReportID(id);
        System.out.println("OutBackService.getOutID:Found out report id:"+res+",from user id:"+id);
        return res;
    }

    /**
     * 提交返校反馈
     * @param id String 用户ID
     * @param actualBackDay java.sql.Date 实际返校时间
     * @param tours List<Tour> 实际出校经过的地点
     * @return 增加返校记录状态成败
     * @throws IOException 数据库读写错误
     */
    public boolean submitBackReport(String id, Date actualBackDay, List<Tour> tours) throws IOException//某用户销假
    {
        boolean res=false;
        if(id!=null&&tours!=null){
            OutReportDAO outReportDAO=new OutReportDAO();
            TourDAO tourDAO=new TourDAO();
            if(outReportDAO.isOut(outReportDAO.getLastOutReportID(id))){
                res=outReportDAO.updateBackTime(outReportDAO.getLastOutReportID(id),actualBackDay);
                for (Tour tour : tours) {
                    tourDAO.addTour(tour);
                }
            }
        }
        System.out.println("OutBackService.submitBackReport:user id:"+id+" has "+(res?" ":" not ")+"submitted back report");
        return res;
    }

    /**
     * 返回OutReport表单中所有UserID（供Track查询用）
     * @return List<String> 用户ID集合
     * @throws IOException 数据库读写错误
     */
    public List<String> getUser() throws IOException {
        List<String> res=new TourDAO().getUser();
        System.out.println("OutBackService.getUser:Have found "+res.size()+" tours!");
        return res;
    }

    /**
     * 通过用户ID返回该用户去了哪些地方
     * @param id String 用户ID
     * @return List<Tour> 地点集合
     * @throws IOException 数据库读写错误
     */
    public List<Tour> getTour(String id) throws IOException {
        int outId=new OutReportDAO().getLastOutReportID(id);
        TourDAO tourDAO=new TourDAO();
        List<Tour> res=tourDAO.findTourByOutID(outId);
        System.out.println("OutBackService.getTour:Have found "+res.size()+" users!");
        return res;
    }

    /**
     * 小单元测试模块
     * @param args String[] 系统参数
     * @throws IOException 数据库读写错误
     */
    public static void main(String[] args) throws IOException {
        OutBackService outBackService=new OutBackService();
        List<String> ids=outBackService.getUser();
        for (String id : ids) {
            System.out.println(id);
        }
        System.out.println(outBackService.existUser("001"));
    }

}
