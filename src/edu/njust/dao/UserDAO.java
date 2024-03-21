package edu.njust.dao;

import edu.njust.entity.Admin;
import edu.njust.entity.User;
import edu.njust.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Vector;

public class UserDAO {
    public UserDAO() {
    }

    /**
     * 根据用户ID查找其密码
     * select Pswd from user where ID = #{id}
     * @param id String 用户ID
     * @return password 返回该用户ID对应的密码
     * @throws IOException 数据库读写错误
     */
    public String findPasswordByID(String id) throws IOException
    {
        String res=null;
        if (id != null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            res = userMapper.queryPswdUserByID(id);
            if (res != null) {
                System.out.println("UserDAO:Have found password:"+res+" of id:"+id);
            } else {
                System.out.println("UserDAO.findPasswordByID:Do not exist this user!");
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 通过ID寻找用户所有信息
     * select ID,Pswd,Name,Department from user where ID = #{id}
     * @param id String 用户ID
     * @return User 用户所有信息
     * @throws IOException 数据库读写错误
     */
    public User findUserByID(String id) throws IOException{
        User res=null;
        if (id != null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                    .build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            res=userMapper.queryUserByID(id);
            if (res != null) {
                System.out.println("UserDAO.findUserByID:Have found:"+res.show());
            } else {
                System.out.println("UserDAO.findUserByID:Do not exist this user!");
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 增加一条用户记录
     * insert into user values(#{ID},#{password},#{name},#{department},0)
     * @param user User 增加的用户信息
     * @return boolean返回操作成功或失败
     * @throws IOException 数据库读写错误
     */
    public boolean addUser(User user) throws IOException
    {
        boolean res = false;
        if (user != null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            int count = userMapper.insertUser(user);
            if (count != 0) {
                res = true;
                System.out.println("UserDAO.addUser:Have inserted:"+user.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 将指定用户修改为管理员
     * update user set IsAdmin=1 where ID=#{id};
     * @param user String 用户ID
     * @return boolean更改操作成败状态
     * @throws IOException 数据库读写错误
     */
    public boolean addAdmin(User user) throws IOException
    {
        boolean res=false;
        if (user != null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            int count=userMapper.addAdmin(user.getID());
            if (count != 0) {
                res = true;
                Admin admin=new Admin(user.getID(),user.getPassword(), user.getName(), user.getDepartment());
                System.out.println("UserDAO.addAdmin:Have inserted:"+ admin.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 取消管理员权限
     * update user set IsAdmin=0 where ID = #{id}
     * @param user User 用户封装
     * @return boolean 操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean cancelAdmin(User user) throws IOException {
        boolean res=false;
        if (user != null) {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            int count=userMapper.cancelAdmin(user.getID());
            if (count != 0) {
                res = true;
                System.out.println("UserDAO.cancelAdmin:Have canceled admin:"+ user.show());
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 批量重置指定学院的密码
     * update user set Pswd=#{password} where Department=#{department}
     * @param Department String 学院名
     * @param afterModifyPassword String 修改后的密码
     * @return boolean 操作成功与否
     * @throws IOException 数据库读写错误
     */
    public boolean batchModifyUser(String Department, String afterModifyPassword) throws IOException//update User set Pswd=afterModifyPswd where Department=Department
    {
        boolean res=false;
        if (Department != null && afterModifyPassword != null) {
            User user=new User(null,afterModifyPassword,null,Department);
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            int count =userMapper.updatePswd(user);
            if (count != 0) {
                res = true;
                System.out.println("UserDAO.batchModifyUser:Have modified password counts:"+ count);
            }
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 通过用户ID修改用户所有数据
     * update user set Pswd=#{password},Department=#{department},Name=#{name} where ID=#{ID}
     * @param user User 用户数据封装
     * @return boolean 修改成败
     * @throws IOException 数据库读写错误
     */
    public boolean modifyUserByID(User user) throws IOException {
        boolean res=false;
        if(user!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                    .build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            int count=userMapper.updateUserByID(user);
            if(count!=0){
                res=true;
            }
            session.commit();
            session.close();
        }
        System.out.println("UserDAO.modifyUserByID:Have"+(res?" ":" not ")+"modified user by id!");
        return res;
    }

    /**
     * 该用户是否为管理员
     * select IsAdmin from user where ID=#{id}
     * @param id String用户ID
     * @return boolean 用户是否为管理员
     * @throws IOException 数据库读写错误
     */
    public boolean isAdmin(String id) throws IOException{
        boolean res=false;
        if(id!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                    .build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            res = userMapper.queryIsAdmin(id);
            System.out.println("UserDAO.isAdmin:User id:"+id+" is"+(res?" ":" not ")+"an admin");
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 查找某学院的用户
     * select ID,Pswd as password,Name as name,Department as department from user where Department=#{dept}
     * @param dept String 学院
     * @return List<User> 某学院用户集合
     * @throws IOException 数据库读写错误
     */
    public List<User> findUserByDept(String dept) throws IOException {
        List<User> res=null;
        if(dept!=null){
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            UserMapper userMapper=session.getMapper(UserMapper.class);
            res=userMapper.queryByDept(dept);
            System.out.println("UserDAO.findUserByDept:Have found "+res.size()+" users of "+dept+"!");
            session.commit();
            session.close();
        }
        return res;
    }

    /**
     * 查找所有用户
     * select ID,Pswd as password,Name as name,Department as department from user
     * @return List<User> 所有用户的数据结构
     * @throws IOException 数据库读写错误
     */
    public List<User> findAllUsers() throws IOException{
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        List<User> res=userMapper.queryAllUser();
        System.out.println("UserDAO.findAllUsers:Have found "+res.size()+" users!");
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
        //User user=new User("918106840229","000000","马运国","计算机科学与工程学院");
        UserDAO userDAO=new UserDAO();
        System.out.println(userDAO.findPasswordByID("918106840206"));
        System.out.println(userDAO.findUserByID("918106840206").show());
        /*System.out.println(userDAO.isAdmin("918106840206"));
        System.out.println(userDAO.isAdmin("918106840229"));
        List<User> users= userDAO.findAllUsers();
        for(int i=0;i< users.size();i++){
            System.out.println(users.get(i).show());
        }
        List<Admin> admins= userDAO.findAllAdmin();
        for(int i=0;i< admins.size();i++){
            System.out.println(admins.get(i).show());
        }*/
        /*userDAO.addUser(user);
        user=new User("918106840206","991011","滕依筱","计算机科学与工程学院");
        userDAO.addUser(user);
        userDAO.addAdmin(user);
        user=new User("918106840738","123456","罗文水","计算机科学与工程学院");
        userDAO.deleteUserByID("918106840738");
        */
        //System.out.println(userDAO.batchModifyUser("计算机科学与工程学院","123456"));

    }
}
