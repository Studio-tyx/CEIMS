package edu.njust.dao;

import edu.njust.entity.OutReport;
import edu.njust.mapper.OutReportMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.util.List;

public class OutReportDAO {

    /**
     * 增加出校申请记录
     * insert into OutReport values(null,#{ID},#{outDate},#{plannedBackDate},#{destination},null)
     * @param or OutReport servlet层封装出校申请信息（使用不包括OutReportID的构造器）
     * @return boolean 增加记录成败状态
     * @throws IOException 数据库读写错误
     */
    public boolean addOutReport(OutReport or) throws IOException//insert into OutReport values{User,Outday,BackDay,hasBack}
    {
        boolean res=false;
        if(or!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            OutReportMapper outReportMapper=session.getMapper(OutReportMapper.class);
            int count=outReportMapper.insertOutReport(or);
            if(count!=0){
                res=true;
                System.out.println("OutReportDAO.addOutReport:Have inserted "+or.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 根据用户ID返回最近一次的出校申请ID
     * select OutReportID from OutReport where OutDate =(select max(OutDate) from OutReport where ID=#{id}) and ID=#{id}
     * @param id String 用户ID
     * @return int outReportID 出校申请ID
     * @throws IOException 数据库读写错误
     */
    public int getLastOutReportID(String id) throws IOException
    {
        int res=0;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            OutReportMapper outReportMapper=session.getMapper(OutReportMapper.class);
            res=outReportMapper.selectReportIDByID(id);
            System.out.println("OutReportDAO.getLastOutReportID:Have found report ID:"+res+",from id:"+id);
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 修改返校时间（管理员修改返校时间或用户填写返校时间，service端区分）
     * update OutReport set ActualBackDate=#{param2} where OutReportID=#{param1}
     * @param orID int outReportID
     * @param actualBackDate Date 实际返校时间
     * @return boolean 修改操作成败状态
     * @throws IOException 数据库读写错误
     */
    public boolean updateBackTime(int orID,Date actualBackDate) throws IOException//insert into BackReport values ID=id,ApproachTime=tour.ApproachTime,Place=tour.place,Residence=tour.residence
    {
        boolean res=false;
        if(orID!=0){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            OutReportMapper outReportMapper=session.getMapper(OutReportMapper.class);
            int count=outReportMapper.updateActualBackDate(orID,actualBackDate);
            if(count!=0){
                res=true;
            }
            System.out.println("OutReportDAO.updateBackTime:Have"+(res?" ":" not ")+"update actualBackDate:"+actualBackDate);
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 某用户是否从未提交过出校申请（该用户刚创建）
     * select outReportID,ID,outDate,plannedBackDate,destination,actualBackDate from OutReport
     * 		where ID=#{id}
     * @param id String 用户ID
     * @return boolean 某用户是否存在于OutReport表单中
     * @throws IOException 数据库读写错误
     */
    public boolean existUser(String id) throws IOException {
        boolean res=false;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            OutReportMapper outReportMapper=session.getMapper(OutReportMapper.class);
            List<OutReport> outReports=outReportMapper.selectUser(id);
            if(outReports.size()!=0){
                res=true;
            }
            System.out.println("OutReportDAO.existUser:The OutReport"+(res?" ":" not ")+"exist id:"+id);
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 根据OutReportID查看某用户是否仍在校外=已提交出校申请但是未销假，也是判断能否填写返校时间的依据
     * select count(OutReportID) from OutReport where ActualBackDate is null and OutReportID=#{orID}
     * @param orID int OutReportID
     * @return boolean 用户是否仍在校外
     * @throws IOException 数据库读写错误
     */
    public boolean isOut(int orID) throws IOException
    {
        boolean res=false;
        if(orID!=0){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            OutReportMapper outReportMapper=session.getMapper(OutReportMapper.class);
            int count=outReportMapper.selectNotBackByID(orID);
            if(count!=0){
                res=true;
            }
            System.out.println("OutReportDAO.isOut:The OutReport id:"+orID+" has"+(res?" not ":" ")+"back to school!");
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 返回仍未返校的学生名单
     * select outReportID,ID,outDate,plannedBackDate,destination,actualBackDate from OutReport
     * 		where ActualBackDate is null
     * @return 仍未返校的学生名单
     * @throws IOException 数据库读写错误
     */
    public List<OutReport> getNotBackOutReport() throws IOException {
        List<OutReport> res;
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        OutReportMapper outReportMapper=session.getMapper(OutReportMapper.class);
        res=outReportMapper.selectReportIDNotBack();
        System.out.println("OutReportDAO.getNotBackOutReport:Have found "+res.size()+" reports of not back users!");
        session.commit();
        session.close();
        return res;
    }


    /**
     * 小单元测试模块
     * @param args String[] 系统参数
     * @throws IOException 数据库读写错误
     */
    public static void main(String[] args) throws IOException
    {
        OutReportDAO outReportDAO=new OutReportDAO();

        //测试增加出校申请
        /*
        Date date=new Date(120,8,12);
        OutReport or=new OutReport("918106840206",date,date,"三号门");
        outReportDAO.addOutReport(or);
         */

        //测试notBack
        String id="918106840206";
        System.out.println(outReportDAO.getLastOutReportID(id));
        System.out.println(outReportDAO.isOut(outReportDAO.getLastOutReportID(id)));
        id="918106840229";
        System.out.println(outReportDAO.isOut(outReportDAO.getLastOutReportID(id)));

        List<OutReport> outReports=outReportDAO.getNotBackOutReport();
        for (OutReport outReport : outReports) {
            System.out.println(outReport.show());
        }

        //测试增加返校日期
        /*
        id="918106840206";
        int outReportID=outReportDAO.getOutReportID(id);
        System.out.println(outReportID);
        System.out.println(outReportDAO.updateBackTime(outReportID,date));
        */

    }
}
