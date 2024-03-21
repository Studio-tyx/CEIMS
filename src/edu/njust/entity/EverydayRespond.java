package edu.njust.entity;

import java.sql.Timestamp;

public class EverydayRespond {
    private String ID;
    private Timestamp SubmitTime;
    private Timestamp ReplyTime;
    private String AdminID;
    private String Msg;

    public EverydayRespond() {
    }

    public EverydayRespond(String ID, Timestamp submitTime, Timestamp replyTime, String adminID, String msg) {
        this.ID = ID;
        this.SubmitTime = submitTime;
        this.ReplyTime = replyTime;
        this.AdminID = adminID;
        this.Msg = msg;
    }

    public EverydayRespond(String ID, Timestamp submitTime, String adminID, String msg) {
        this.ID = ID;
        this.SubmitTime = submitTime;
        this.ReplyTime=new Timestamp(System.currentTimeMillis());
        this.AdminID = adminID;
        this.Msg = msg;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Timestamp getSubmitTime() {
        return SubmitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        SubmitTime = submitTime;
    }

    public Timestamp getReplyTime() {
        return ReplyTime;
    }

    public void setReplyTime() {
        ReplyTime = new Timestamp(System.currentTimeMillis());
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String show(){
        return "Respond to user id:"+this.ID+",submit time:"+this.SubmitTime
                +",reply from admin id:"+this.AdminID+",at time:"+this.ReplyTime+",reply message:"+this.Msg;
    }
}
