package edu.njust.entity;

import javax.print.DocFlavor;
import java.sql.Date;

public class OutReport {
    private int outReportID=0;
    private String ID;
    private Date outDate;//java.sql.Date
    private Date plannedBackDate;//java.sql.Date
    private String destination;
    private Date actualBackDate;

    public String show()
    {
        return "OutReport ID:"+this.outReportID+",from user id:"+this.ID+",planned out date:" +this.outDate
                +",planned back date:"+this.plannedBackDate+",destination:"+this.destination +",actual back date:"+this.actualBackDate;
    }

    public OutReport() {
    }

    public OutReport(String ID, Date outDate, Date plannedBackDate, String destination) {
        this.ID = ID;
        this.outDate = outDate;
        this.plannedBackDate = plannedBackDate;
        this.destination = destination;
        this.actualBackDate=null;
    }

    public int getOutReportID() {
        return outReportID;
    }

    public void setOutReportID(int outReportID) {
        this.outReportID = outReportID;
    }

    public String getId() {
        return ID;
    }

    public void setId(String ID) {
        this.ID = ID;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Date getPlannedBackDate() {
        return plannedBackDate;
    }

    public void setPlannedBackDate(Date plannedBackDate) {
        this.plannedBackDate = plannedBackDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getActualBackDate() {
        return actualBackDate;
    }

    public void setActualBackDate(Date actualBackDate) {
        this.actualBackDate = actualBackDate;
    }
}
