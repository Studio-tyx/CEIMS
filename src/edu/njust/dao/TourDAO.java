package edu.njust.dao;

import edu.njust.entity.Tour;
import edu.njust.mapper.TourMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TourDAO {
    /**
     * 查找某一出校申请到达的地点
     * select outReportID,approachTime,province,city,area,address from Tour where OutReportID=#{orID}
     * @param orID int OutReportID
     * @return List<Tour> 该出校申请对应出校过程中到达的地点
     * @throws IOException 数据库读写错误
     */
    public List<Tour> findTourByOutID(int orID) throws IOException
    {
        List<Tour> res=null;
        if(orID!=0){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            TourMapper tourMapper=session.getMapper(TourMapper.class);
            res=tourMapper.selectTourByOutID(orID);
            System.out.println("TourDAO.findTourByOutID:Have found "+res.size()+" tours of OutReportID:"+orID);
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 增加出行记录
     * insert into Tour values (#{outReportID},#{approachTime},#{province},#{city},#{area},#{address})
     * @param tour Tour 旅途封装
     * @return boolean 添加记录成败状态
     * @throws IOException 数据库读写错误
     */
    public boolean addTour(Tour tour) throws IOException
    {
        boolean res=false;
        if(tour!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            TourMapper tourMapper=session.getMapper(TourMapper.class);
            int count=tourMapper.insertTour(tour);
            if(count!=0){
                res=true;
                System.out.println("TourDAO.addTour:Have insert "+tour.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 获取Tour表单中所有用户
     * @return List<String> 所有用户ID集合
     * @throws IOException 数据库读写错误
     */
    public List<String> getUser() throws IOException {
        List<String> res;
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        TourMapper tourMapper=session.getMapper(TourMapper.class);
        res=tourMapper.selectAllUser();
        System.out.println("TourDAO.getUser:Have found "+res.size()+" id!");
        session.commit();
        session.close();
        return res;
    }

    /**
     * 小单元测试模块
     * @param args String[] 系统参数
     * @throws IOException 数据库读写错误
     */
    public static void main(String []args) throws IOException {
        String id="918106840206";
        TourDAO tourDAO=new TourDAO();
        OutReportDAO outReportDAO=new OutReportDAO();

        //测试增加tour
        /*
        Timestamp time=new Timestamp(120,8,13,14,0,0,0);
        Tour tour=new Tour(3,time,"江苏","南京","玄武","紫金商业街");
        tourDAO.addTour(tour);
        */

        //测试根据用户查询地点
        List<Tour> tours=tourDAO.findTourByOutID(outReportDAO.getLastOutReportID(id));
        for (Tour tour : tours) {
            System.out.println(tour.show());
        }
    }
}
