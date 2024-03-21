package edu.njust.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.DateTimeException;

public class EverydayReport {
    private String ID;
    private Timestamp submitTime;//自动生成
    private String tel;
    private String addr;
    private double temperature;
    private String remark;

    public EverydayReport() {
    }

    public EverydayReport(String ID, String tel, double temperature) {
        this.ID = ID;
        this.submitTime=new Timestamp(System.currentTimeMillis());
        this.tel = tel;
        this.addr = null;
        this.temperature = temperature;
        this.remark = null;
    }

    public EverydayReport(String ID, String tel, String addr, double temperature) {
        this.ID = ID;
        this.submitTime=new Timestamp(System.currentTimeMillis());
        this.tel = tel;
        this.addr = addr;
        this.temperature = temperature;
        this.remark = null;
    }

    public EverydayReport(String ID, String tel, double temperature, String remark) {
        this.ID = ID;
        this.submitTime=new Timestamp(System.currentTimeMillis());
        this.tel = tel;
        this.addr = null;
        this.temperature = temperature;
        this.remark = remark;
    }

    public EverydayReport(String ID, String tel, String addr, double temperature, String remark) {
        this.ID = ID;
        this.submitTime=new Timestamp(System.currentTimeMillis());
        this.tel = tel;
        this.addr = addr;
        this.temperature = temperature;
        this.remark = remark;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime() {
        this.submitTime = new Timestamp(System.currentTimeMillis());
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String show(){
        return "Report userID:"+ID+",submitTime:"+submitTime+",telephone:"+tel+",addr:"+addr+",temperature:"+temperature+",remark:"+remark;
    }
}
