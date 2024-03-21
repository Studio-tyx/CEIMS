package edu.njust.entity;


import java.sql.Timestamp;

public class Tour {
    private int outReportID;
    private Timestamp approachTime;
    private String province;
    private String city;
    private String area;
    private String address;

    public String show(){
        return "Tour from outReportID:"+this.outReportID+",approach time:"+this.approachTime
                +",province:"+this.province+",city"+this.city+",area:"+this.area+",address:"+this.address;
    }

    public Tour() {
    }

    public Tour(int outReportID, Timestamp approachTime, String province, String city, String area, String address) {
        this.outReportID = outReportID;
        this.approachTime = approachTime;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
    }

    public int getOutReportID() {
        return outReportID;
    }

    public void setOutReportID(int outReportID) {
        this.outReportID = outReportID;
    }

    public Timestamp getApproachTime() {
        return approachTime;
    }

    public void setApproachTime(Timestamp approachTime) {
        this.approachTime = approachTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
