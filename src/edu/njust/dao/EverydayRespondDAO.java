package edu.njust.dao;

import edu.njust.entity.EverydayRespond;
import edu.njust.mapper.EverydayRespondMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class EverydayRespondDAO {

    /**
     * 增加每日申报的回答
     * insert into EverydayRespond values(#{ID},#{submitTime},#{replyTime},#{adminID},#{msg})
     * @param erp EverydayRespond 回应信息封装
     * @return boolean 增加记录是否成功
     * @throws IOException 数据库读写错误
     */
    public boolean addRespond(EverydayRespond erp) throws IOException{
        boolean res=false;
        if(erp!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayRespondMapper everydayRespondMapper=session.getMapper(EverydayRespondMapper.class);
            int count=everydayRespondMapper.insertEverydayRespond(erp);
            if (count != 0) {
                res = true;
                System.out.println("EverydayRespondDAO.addRespond:Have inserted "+erp.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 根据用户ID和提交每日申报的时间返回该申报文件 管理员的回应
     * select Msg from EverydayRespond where ID=#{param1} and SubmitTime=#{param2}
     * @param id String 用户ID
     * @return 该申报文件 管理员的回应
     * @throws IOException 数据库读写错误
     */
    public List<EverydayRespond> findMsgByID(String id) throws IOException {
        List<EverydayRespond> res=null;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            EverydayRespondMapper everydayRespondMapper=session.getMapper(EverydayRespondMapper.class);
            res=everydayRespondMapper.selectMsgByID(id);
            System.out.println("EverydayRespondDAO.findMsgByID:find "+res.size()+" responds of id:"+id);
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
    public static void main(String []args) throws IOException{
        EverydayRespondDAO everydayRespondDAO=new EverydayRespondDAO();

        //测试增加记录
        //EverydayReportDAO everydayReportDAO=new EverydayReportDAO();
        //Timestamp time=everydayReportDAO.findNewestTime("918106840229");
        //EverydayRespond erp=new EverydayRespond("918106840229",time,"918106840206","hey");
        //everydayRespondDAO.addRespond(erp);

        //测试读取记录
        System.out.println(everydayRespondDAO.findMsgByID("918106840229"));
    }
}
