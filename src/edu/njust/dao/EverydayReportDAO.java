package edu.njust.dao;

import edu.njust.entity.EverydayReport;
import edu.njust.mapper.EverydayReportMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.List;

public class EverydayReportDAO {
    /**
     * 每日申报提交
     * insert into EverydayReport values(#{ID},#{submitTime},#{tel},#{addr},#{temperature},#{remark})
     * @param er EverydayReport 每日申报封装(ID,submitTime,tel,addr,temperature,remark)
     * @return boolean 增加记录操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean addEveryReport(EverydayReport er) throws IOException
    {
        boolean res = false;
        if (er != null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
            int count=everydayReportMapper.insertEverydayReport(er);
            if (count != 0) {
                res = true;
                System.out.println("EverydayReportDAO.addEveryReport:Have inserted "+er.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 查询第七次提交的时间
     * select SubmitTime from everydayReport where ID=#{id} order by SubmitTime desc limit 6,1
     * @param id String 用户ID
     * @return Timestamp 第七次提交记录的时间
     * @throws IOException 数据库读写错误
     */
    public Timestamp findSeventhReport(String id) throws IOException
    {
        Timestamp time=null;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
            time=everydayReportMapper.selectSeventhReport(id);
            if(time!=null){
                System.out.println("EverydayReportDAO.findSeventhReport:Have found seventh submit time:"+time+",from id:"+id);
            }
            session.commit();
            session.close();
        }
        return time;
    }

    /**
     * 删除指定日期之前的申报记录
     * delete from EverydayReport where ID=#{param1} and SubmitTime &lt; #{param2}
     * @param id String 用户ID
     * @param time Timestamp
     * @return boolean 删除操作成败状态
     * @throws IOException 数据库读写错误
     */
    public boolean delBeforeReport(String id,Timestamp time) throws IOException
    {
        boolean res = false;
        if (id!=null && time!=null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
            int count=everydayReportMapper.deleteAfterSevenDay(id,time);
            if (count != 0) {
                res = true;
                System.out.println("EverydayReportDAO.delBeforeReport:Have deleted report id:"+ id+" before seven days");
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 查询该用户最新申报的时间
     * select max(SubmitTime) from EverydayReport where ID=#{id}
     * @param id String 用户ID
     * @return Timestamp 最新申报时间戳
     * @throws IOException 数据库读写错误
     */
    public Timestamp findNewestTime(String id) throws IOException
    {
        Timestamp res=null;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
            res=everydayReportMapper.selectNewestTime(id);
            System.out.println("EverydayReportDAO.findNewestTime:Have found newest time:"+res+",of id:"+id);
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 查看体温异常用户
     * select ID,SubmitTime as submitTime,Tel as tel,Addr as addr,Temperature as temperature,Remark as remark
     * 	    from EverydayReport where Temperature &lt; 36.0 or Temperature &gt; 37.2
     * @return List<EverydayReport> 返回体温异常用户集合
     * @throws IOException 数据库读写错误
     */
    public List<EverydayReport> findDateReport(Timestamp time1,Timestamp time2) throws IOException
    {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
        List<EverydayReport> res=everydayReportMapper.selectAll(time1,time2);
        System.out.println("EverydayReportDAO.findDateReport:Have found "+res.size()+" reports after "+time1+",and before "+time2+"!");
        session.commit();
        session.close();
        return res;
    }

    /**
     * 返回未被回复的每日申报表
     * select EverydayReport.ID,EverydayReport.SubmitTime as submitTime,EverydayReport.Tel as tel,
     * 	    EverydayReport.Addr as addr,EverydayReport.Temperature as temperature,EverydayReport.Remark as remark
     * 	    from EverydayReport left join EverydayRespond
     * 	    on EverydayReport.id=EverydayRespond.id and EverydayRespond.submittime=EverydayReport.submittime
     * 	    where EverydayRespond.id is null and EverydayReport.remark is not null
     * @return List<EverydayReport> 未被回复的每日申报表
     * @throws IOException 数据库读写错误
     */
    public List<EverydayReport> findUnansweredRemark() throws IOException
    {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
        List<EverydayReport> res=everydayReportMapper.selectUnanswered();
        System.out.println("EverydayReportDAO.findUnansweredRemark:Have found "+res.size()+" unanswered reports!");
        return res;
    }

    /**
     * 修改用户特定日期体温
     * update EverydayReport set Temperature=#{param3} where ID=#{param1} and SubmitTime=#{param2}
     * @param id String 用户ID
     * @param time Timestamp 特定日期
     * @param newTemperature double 体温
     * @return 修改结果状态
     * @throws IOException 数据库读写错误
     */
    public boolean updateTemperatureByID(String id,Timestamp time,double newTemperature) throws IOException{
        boolean res=false;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayReportMapper everydayReportMapper=session.getMapper(EverydayReportMapper.class);
            int count=everydayReportMapper.updateTemperature(id,time,newTemperature);
            if(count!=0){
                res=true;
                System.out.println("EverydayReportDAO.updateTemperatureByID:Have update temperature:"+newTemperature+" of id:"+id+",time:"+time);
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 小单元测试模块
     * @param args String[] 系统参数
     * @throws IOException 数据库读写错误
     */
    public static void main(String[] args) throws IOException {
        EverydayReport er=new EverydayReport("918106840206","13757142557","宁静轩",37.8,"隔离");
        er.show();
        EverydayReportDAO everydayReportDAO=new EverydayReportDAO();
        String id="918106840206";
        System.out.println(everydayReportDAO.findNewestTime(id));

        //测试增加用户记录
        /*
        System.out.println(everydayReportDAO.hasNewestReport("918106840206"));
        System.out.println(everydayReportDAO.addEveryReport(er));
        System.out.println(everydayReportDAO.hasNewestReport("918106840206"));
        */

        //测试增加记录
        //System.out.println(everydayReportDAO.addEveryReport(er));

        //测试删除以前的记录
        /*
        Timestamp time=everydayReportDAO.findSeventhReport(id);
        System.out.println(time);
        System.out.println(everydayReportDAO.delBeforeReport(id,time));
        */

        //测试读取体温异常者数据
        //List<EverydayReport> everydayReports=everydayReportDAO.findAllReport();
        //for(int i=0;i<everydayReports.size();i++){
        //    System.out.println(everydayReports.get(i).show());
        //}

        //测试读取未回复信息
        /*
        everydayReports=everydayReportDAO.findUnansweredRemark();
        for(int i=0;i<everydayReports.size();i++){
            System.out.println(everydayReports.get(i).show());
        }
         */

        //测试修改体温
        /*
        Timestamp time=everydayReportDAO.findNewestTime(id);
        System.out.println(everydayReportDAO.updateTemperatureByID(id,time,37.1));
         */

    }

}
