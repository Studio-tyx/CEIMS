package edu.njust.service;

import edu.njust.dao.UserDAO;
import edu.njust.entity.User;

import java.io.IOException;
import java.util.List;

public class UserService {

    /**
     * 登录
     * @param user User 用户信息封装
     * @return boolean 操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean login(User user) throws IOException//根据用户账号密码判断是否登录成功
    {
        boolean res=false;
        if(user!=null){
            UserDAO userDAO=new UserDAO();
            String password=userDAO.findPasswordByID(user.getID());
            if(password.equals(user.getPassword())){
                res=true;
            }
            System.out.println("UserService.login:User:"+user.getID()+" login "+(res?"successfully!":"failed!"));
        }
        return res;
    }

    /**
     * 根据ID查找用户所有信息
     * @param id String 用户ID
     * @return User 用户信息封装
     * @throws IOException 数据库读写错误
     */
    public User findUser(String id) throws IOException {
        User user=null;
        if(id!=null){
            UserDAO userDAO=new UserDAO();
            user=userDAO.findUserByID(id);
            System.out.println("UserService.findUser:Have found :"+user.show());
        }
        return user;
    }

    /**
     * 某用户是否为管理员
     * @param user User 用户信息封装
     * @return boolean 该用户是否为管理员
     * @throws IOException 数据库读写错误
     */
    public boolean isAdmin(User user) throws IOException {
        boolean res=false;
        if(user!=null){
            UserDAO userDAO=new UserDAO();
            res=userDAO.isAdmin(user.getID());
            System.out.println("UserService.isAdmin:User:"+user.getID()+" is"+(res?" ":" not ")+"an Admin!");
        }
        return res;
    }

    /**
     * 数据库中是否存在用户（该用户是否被注册）
     * @param id String 用户ID
     * @return boolean 是否存在
     * @throws IOException 数据库读写错误
     */
    public boolean existUser(String id) throws IOException {
        boolean res=false;
        if(id!=null){
            UserDAO userDAO=new UserDAO();
            User user=userDAO.findUserByID(id);
            if(user!=null){
                res=true;
            }
            System.out.println("UserService.existUser:"+(res?"":"Not ")+"exist user id:"+id);
        }
        return res;
    }

    /**
     * 增加用户记录
     * @param user User 封装用户信息
     * @return boolean 操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean addUser(User user) throws IOException//根据账号密码注册用户
    {
        boolean res=false;
        if(user!=null){
            UserDAO userDAO=new UserDAO();
            res=userDAO.addUser(user);
            System.out.println("UserService.addUser:Have"+(res?" ":" not ")+"inserted user whose id is:"+user.getID());
        }
        return res;
    }

    /**
     * 增加管理员权限
     * @param user 被赋予管理员权限的用户信息封装
     * @return boolean 操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean addAdmin(User user) throws IOException {
        boolean res=false;
        if(user!=null){
            UserDAO userDAO=new UserDAO();
            res=userDAO.addAdmin(user);
            System.out.println("UserService.addAdmin:Have"+(res?" ":" not ")+"inserted admin whose id is:"+user.getID());
        }
        return res;
    }

    /**
     * 修改用户信息
     * @param user User 用户信息封装
     * @return boolean 修改操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean modifyUser(User user) throws IOException {
        boolean res=false;
        if(user!=null){
            UserDAO userDAO=new UserDAO();
            res=userDAO.modifyUserByID(user);
        }
        System.out.println("UserService.modifyUser:Have"+(res?" ":" not ")+"modified user!");
        return res;
    }

    /**
     * 取消管理员权限
     * @param user User 被取消权限的用户
     * @return 取消操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean cancelAdmin(User user) throws IOException {
        boolean res=false;
        if(user!=null){
            UserDAO userDAO=new UserDAO();
            res=userDAO.cancelAdmin(user);
        }
        System.out.println("UserService.cancelAdmin:Have"+(res?" ":" not ")+"canceled admin!");
        return res;
    }

    /**
     * 根据学院批量重置密码
     * @param department String 学院名
     * @param afterModifyPswd String 重置后密码
     * @return boolean 操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean batchModifyUser(String department, String afterModifyPswd) throws IOException//根据学院批量修改密码
    {
        boolean res=false;
        if(department!=null && afterModifyPswd!=null){
            UserDAO userDAO=new UserDAO();
            res=userDAO.batchModifyUser(department, afterModifyPswd);
        }
        System.out.println("UserService.batchModifyUser:Have"+(res?" ":" not ")+"modified users' password to "+afterModifyPswd
                +",whose department is:"+department);
        return res;
    }

    /**
     * 某学院的用户
     * @param dept String 学院
     * @return List<User> 某学院用户集合
     * @throws IOException 数据库读写错误
     */
    public List<User> findUserByDept(String dept) throws IOException {
        List<User> res=new UserDAO().findUserByDept(dept);
        System.out.println("UserService.findUserByDept:Have found "+res.size()+" users!");
        return res;
    }

    /**
     * 查找所有用户
     * @return List<User> 用户集合
     * @throws IOException 数据库读写错误
     */
    public List<User> findAllUser() throws IOException{
        List<User> res=new UserDAO().findAllUsers();
        System.out.println("UserService.findAllUsers:Have found "+res.size()+" users!");
        return res;
    }

}
