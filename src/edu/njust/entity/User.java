package edu.njust.entity;

public class User {
    private String ID;
    private String password;
    private String name;
    private String department;

    public User() {
    }

    public User(String ID, String password, String name, String department) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.department = department;
    }

    //留作修改密码用的数据结构
    public User(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public String show(){
        return "User id:"+this.ID+",password:"+this.password+",name:"+this.name+",department:"+this.department;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
