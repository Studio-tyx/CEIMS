package edu.njust.mapper;

import edu.njust.entity.Admin;
import edu.njust.entity.User;

import java.util.List;


public interface UserMapper {
    String queryPswdUserByID(String id);
    User queryUserByID(String id);
    int insertUser(User user);
    int addAdmin(String id);
    int cancelAdmin(String id);
    int deleteUser(String id);
    int updatePswd(User user);
    int updateUserByID(User user);
    boolean queryIsAdmin(String id);
    List<User> queryByDept(String dept);
    List<User> queryAllUser();
    List<Admin> queryAllAdmin();
}
