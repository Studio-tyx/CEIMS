package edu.njust.entity;

public class Admin extends User{
    public Admin() {
    }

    public Admin(String ID, String password, String name, String department) {
        super(ID, password, name, department);
    }

    @Override
    public String show() {
        return "Admin id:"+super.getID()+",password:"+super.getPassword()+",name:"+super.getName()+",department:"+super.getDepartment();
    }
}
