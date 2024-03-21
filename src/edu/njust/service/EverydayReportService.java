package edu.njust.service;

import edu.njust.dao.EverydayReportDAO;
import edu.njust.dao.EverydayRespondDAO;
import edu.njust.dao.UserDAO;
import edu.njust.entity.EverydayReport;
import edu.njust.entity.EverydayRespond;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EverydayReportService {

    /**
     * 该用户是否已提交今日申报
     * @param id String用户ID
     * @return boolean 该用户是否已提交今日申报
     * @throws IOException 数据库读写错误
     */
    public boolean hasSubmitEveryReport(String id) throws IOException//每日是否已提交
    {
        boolean res=false;
        if(id!=null){
            Timestamp time=new EverydayReportDAO().findNewestTime(id);
            if(time==null){return false;}
            Date timeDate=new Date(time.getYear(),time.getMonth(),time.getDate());
            Date now=new Date(System.currentTimeMillis());
            String timeString=timeDate.toString();
            String nowString=now.toString();
            res=(timeString.equalsIgnoreCase(nowString));
            System.out.println("EverydayReportService.hasSubmitEveryReport:id:"+id+" has "+(res?"":"not ")+"newest report!");
        }
        return res;
    }

    /**
     * 提交每日申报信息
     * @param er EverydayReport servlet端封装的每日申报信息
     * @return boolean 提交操作成败状态
     * @throws IOException 数据库读写错误
     */
    public boolean submitEveryReport(EverydayReport er) throws IOException//提交（无地址无备注）
    {
        boolean res=false;
        if(er!=null){
            EverydayReportDAO everydayReportDAO=new EverydayReportDAO();
            Timestamp time=everydayReportDAO.findSeventhReport(er.getID());//查找第七次记录时间
            everydayReportDAO.delBeforeReport(er.getID(),time);//删除七天以前的记录
            res=everydayReportDAO.addEveryReport(er);
            System.out.println("EverydayReportService.submitEveryReport:Have"+(res?" ":" not ")+"submitted "+er.show());
        }
        return res;
    }

    /**
     * 查找体温异常用户
     * @return List<EverydayReport> 返回体温异常用户集合
     * @throws IOException 数据库读写错误
     */
    public List<EverydayReport> findTodayReport() throws IOException{

        Timestamp now=new Timestamp(System.currentTimeMillis());
        Timestamp time1=now;
        time1.setHours(0);time1.setMinutes(0);time1.setSeconds(0);time1.setNanos(0);
        Timestamp time2=new Timestamp(time1.getYear(),time1.getMonth(),time1.getDate()+1,time1.getHours(),time1.getMinutes(),time1.getSeconds(),time1.getNanos());
        List<EverydayReport> res=new EverydayReportDAO().findDateReport(time1,time2);
        System.out.println("EverydayReportService.findTodayReport:Have found "+res.size()+" users whose temperature is abnormal");
        return res;
    }

    /**
     * 查找未被回复的每日申报表单
     * @return List<EverydayReport> 未被回复的申报表
     * @throws IOException 数据库读写错误
     */
    public List<EverydayReport> findUnansweredRemark() throws IOException
    {
        List<EverydayReport> res=new EverydayReportDAO().findUnansweredRemark();
        System.out.println("EverydayReportService.findUnansweredRemark:Have found "+res.size()+" every day reports which has not been answered");
        return res;
    }

    /**
     * 管理员回复用户信息（在方法中已校验管理员权限）
     * @param erp EverydayRespond 回复信息封装
     * @return boolean 增加记录成败
     * @throws IOException 数据库读写错误
     */
    public boolean replyRemark(EverydayRespond erp) throws IOException//根据时间回复消息
    {
        boolean res=false;
        if(erp!=null){
            if(new UserDAO().isAdmin(erp.getAdminID())){
                EverydayRespondDAO everydayRespondDAO=new EverydayRespondDAO();
                res=everydayRespondDAO.addRespond(erp);
            }
            System.out.println("EverydayReportService.replyRemark:Admin id:"+erp.getAdminID()+"have"+(res?" ":" not ")
                    +"answered user's remark for "+erp.getMsg()+",whose user id:"+erp.getID());
        }
        return res;
    }

    /**
     * 根据用户ID获取该用户被回复的信息
     * @param id String 用户ID
     * @return List<EverydayRespond> 用户信息集合
     * @throws IOException 数据库读写错误
     */
    public List<EverydayRespond> getEveryRespond(String id) throws IOException {
        List<EverydayRespond> res=null;
        if(id!=null){
            res=new EverydayRespondDAO().findMsgByID(id);
            System.out.println("EverydayReportService.getEveryRespond:Found "+res.size()+" responds of id:"+id);
        }
        return res;
    }

    /**
     * 小单元测试模块
     * @param args String[] 系统参数
     * @throws IOException 数据库读写错误
     */
    public static void main(String []args) throws IOException {
        EverydayReportService everydayReportService=new EverydayReportService();
        List<EverydayReport> res=everydayReportService.findTodayReport();
        for (EverydayReport re : res) {
            System.out.println(re.show());
        }
        List<EverydayRespond> responds=everydayReportService.getEveryRespond("918106840229");
        for(int i=0;i<res.size();i++){
            System.out.println(responds.get(i).show());
        }
    }

}
