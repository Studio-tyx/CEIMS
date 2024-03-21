package edu.njust.dao;

import edu.njust.entity.EpidemicNumber;
import edu.njust.mapper.CampusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;

public class CampusNumDAO {

    /**
     * 增加全校记录
     * insert into CampusNum values (#{date},#{XYQZ},#{WZZ},#{XYYS},#{XYZZ},#{LJQZ},#{JWSR},#{LJZY},#{LJSW},
     * 		#{dXYQZ},#{dWZZ},#{dXYYS},#{dXYZZ},#{dLJQZ},#{dJWSR},#{dLJZY},#{dLJSW})
     * @param epidemicNumber EpidemicNumber
     * @return boolean 增加操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean addCampusNum(EpidemicNumber epidemicNumber) throws IOException {
        boolean res=false;
        if(epidemicNumber !=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            CampusMapper campusMapper=session.getMapper(CampusMapper.class);
            int count = campusMapper.insertCampus(epidemicNumber);
            if(count!=0){
                res=true;
            }
            session.commit();
            session.close();
            System.out.println("CampusNumDAO.addCampusNum:Have"+(res?" ":" not ")+"added "+ epidemicNumber.show());
        }
        return res;
    }

    /**
     * 更新全校数据
     * update CampusNum
     *         set XYQZ=#{XYQZ},WZZ=#{WZZ},XYYS=#{XYYS},XYZZ=#{XYZZ},LJQZ=#{LJQZ},JWSR=#{JWSR},LJZY=#{LJZY},LJSW=#{LJSW},
     *         dXYQZ=#{dXYQZ},dWZZ=#{dWZZ},dXYYS=#{dXYYS},dXYZZ=#{dXYZZ},dLJQZ=#{dLJQZ},dJWSR=#{dJWSR},dLJZY=#{dLJZY},dLJSW=#{dLJSW}
     *         where Date=#{date}
     * @param epidemicNumber EpidemicNumber全校数据封装
     * @return boolean 更新操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean updateCampus(EpidemicNumber epidemicNumber) throws IOException {
        boolean res=false;
        if(epidemicNumber !=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            CampusMapper campusMapper=session.getMapper(CampusMapper.class);
            int count = campusMapper.updateCampus(epidemicNumber);
            if(count!=0){
                res=true;
            }
            session.commit();
            session.close();
            System.out.println("CampusNumDAO.updateCampus:Have"+(res?" ":" not ")+"updated "+ epidemicNumber.show());
        }
        return res;
    }

    /**
     * 某天是否已经有全校记录
     * if(select *from CampusNum where Date=#{date})
     * @param date Date 日期
     * @return boolean 该日是否已经有全校记录
     * @throws IOException 数据库读写错误
     */
    public boolean hasRegister(Date date) throws IOException {
        boolean res=false;
        if (date!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            CampusMapper campusMapper=session.getMapper(CampusMapper.class);
            EpidemicNumber en=campusMapper.selectByDate(date);
            if(en!=null){
                res=true;
            }
            System.out.println("CampusNumDAO.hasRegister:Date "+date+" has "+(res?" ":" not ")+"register");
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 根据日期获取全校记录
     * select *from CampusNum where Date=#{date}
     * @param date Date 日期
     * @return EpidemicNumber 全校记录
     * @throws IOException 数据库读写错误
     */
    public EpidemicNumber getCampusByDate(Date date) throws IOException {
        EpidemicNumber res=null;
        if (date!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            CampusMapper campusMapper=session.getMapper(CampusMapper.class);
            res=campusMapper.selectByDate(date);
            if(res!=null){
                System.out.println("CampusNumDAO:Have found "+ res.show());
            }else{
                System.out.println("CampusNumDAO.getCampusByDate:Have not found register date:"+date);
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
    public static void main(String []args) throws IOException {
        CampusNumDAO campusNumDAO=new CampusNumDAO();
        Date date=new Date(120,8,20);
        System.out.println(date);
        System.out.println(campusNumDAO.getCampusByDate(date));
        date=new Date(System.currentTimeMillis());
        EpidemicNumber en=new EpidemicNumber(date,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        campusNumDAO.addCampusNum(en);
    }
}
