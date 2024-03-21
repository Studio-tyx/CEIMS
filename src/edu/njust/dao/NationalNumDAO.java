package edu.njust.dao;

import edu.njust.entity.EpidemicNumber;
import edu.njust.mapper.NationalMapper;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class NationalNumDAO {

    /**
     * 增加全国数据
     * insert into NationalNum
     * 		values (#{date},#{XYQZ},#{WZZ},#{XYYS},#{XYZZ},#{LJQZ},#{JWSR},#{LJZY},#{LJSW},
     * 		#{dXYQZ},#{dWZZ},#{dXYYS},#{dXYZZ},#{dLJQZ},#{dJWSR},#{dLJZY},#{dLJSW})
     * @param epidemicNumber EpidemicNumber 全国数据封装
     * @return boolean 增加数据成败
     * @throws IOException 数据库读写错误
     */
    public boolean addNum(EpidemicNumber epidemicNumber) throws IOException {
        boolean res=false;
        if(epidemicNumber !=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            NationalMapper nationalMapper=session.getMapper(NationalMapper.class);
            int count = nationalMapper.insertNationalNum(epidemicNumber);
            if(count!=0){
                res=true;
            }
            session.commit();
            session.close();
            System.out.println("NationalNumDAO.addNum:Have"+(res?" ":" not ")+"added "+ epidemicNumber.show());
        }
        return res;
    }

    /**
     * 修改全国数据
     * update NationalNum
     *         set XYQZ=#{XYQZ},WZZ=#{WZZ},XYYS=#{XYYS},XYZZ=#{XYZZ},LJQZ=#{LJQZ},JWSR=#{JWSR},LJZY=#{LJZY},LJSW=#{LJSW},
     *         dXYQZ=#{dXYQZ},dWZZ=#{dWZZ},dXYYS=#{dXYYS},dXYZZ=#{dXYZZ},dLJQZ=#{dLJQZ},dJWSR=#{dJWSR},dLJZY=#{dLJZY},dLJSW=#{dLJSW}
     *         where Date=#{date}
     * @param epidemicNumber EpidemicNumber 全国数据封装
     * @return boolean 修改操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean updateNum(EpidemicNumber epidemicNumber) throws IOException {
        boolean res=false;
        if(epidemicNumber !=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            NationalMapper nationalMapper=session.getMapper(NationalMapper.class);
            int count=nationalMapper.updateNationalByDate(epidemicNumber);
            if(count!=0){
                res=true;
            }
            System.out.println("NationalNumDAO.updateNum:Have"+(res?" ":" not ")+"updated "+ epidemicNumber.show());
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 获取数据库中最新的记录日期
     * select max(Date) from NationalNum
     * @return Date日期
     * @throws IOException 数据库读写错误
     */
    public Date getNewestDate() throws IOException {
        Date date;
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        NationalMapper nationalMapper=session.getMapper(NationalMapper.class);
        date=nationalMapper.selectNewest();
        System.out.println("NationalNumDAO.getNewestDate:Have found newest date:"+date);
        session.commit();
        session.close();
        return date;
    }

    /**
     * 通过日期获取该日全国数据
     * select * from NationalNum where Date=#{date}
     * @param date Date 日期
     * @return EpidemicNumber 全国数据
     * @throws IOException 数据库读写错误
     */
    public EpidemicNumber getNumByDate(Date date) throws IOException {
        EpidemicNumber res=null;
        if(date!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            NationalMapper nationalMapper=session.getMapper(NationalMapper.class);
            res=nationalMapper.selectNumByDate(date);
            System.out.println("NationalNumDAO.getNumByDate:Have found national num:"+res.show());
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
        NationalNumDAO nationalNumDAO=new NationalNumDAO();
        int[] array2 ={1,2,3,4,5,6,7,8};
        int[] array1 ={10,20,30,40,50,60,70,80};
        Date date=new Date(120,9,20);
        //System.out.println(date);
        //EpidemicNumber epidemicNumber =new EpidemicNumber(date,array1,array2);
        //System.out.println(nationalNumDAO.addNum(nationalNum));
        //System.out.println(nationalNumDAO.getNewestDate());
        System.out.println(nationalNumDAO.getNumByDate(nationalNumDAO.getNewestDate()).show());

    }

}
