package edu.njust.entity;

import java.sql.Date;

public class EpidemicNumber {
    private Date date;
    private int XYQZ;
    private int WZZ;
    private int XYYS;
    private int XYZZ;
    private int LJQZ;
    private int JWSR;
    private int LJZY;
    private int LJSW;
    private int dXYQZ;
    private int dWZZ;
    private int dXYYS;
    private int dXYZZ;
    private int dLJQZ;
    private int dJWSR;
    private int dLJZY;
    private int dLJSW;

    public String show(){
        return "Num: date:"+this.date+",现有确诊:"+this.XYQZ+",无症状:"+this.WZZ+",现有疑似:"+this.XYYS+",现有重症:"+this.XYZZ
                +",累计确诊:"+this.LJQZ+",境外输入:"+this.JWSR+",累计治愈:"+this.LJZY+",累计死亡"+this.LJSW
                +",新增现有确诊:"+this.dXYQZ+",新增无症状:"+this.dWZZ+",新增现有疑似:"+this.dXYYS+",新增现有重症:"+this.dXYZZ
                +",新增累计确诊:"+this.dLJQZ+",新增境外输入:"+this.dJWSR+",新增累计治愈:"+this.dLJZY+",新增累计死亡"+this.dLJSW;
    }

    public boolean equals(EpidemicNumber other)
    {
        boolean res=this.show().equalsIgnoreCase(other.show());
        return res;
    }

    public EpidemicNumber() {
    }

    public EpidemicNumber(Date date, int[] array2) {
        this.date = date;
        this.dXYQZ=array2[0];
        this.dWZZ=array2[1];
        this.dXYYS=array2[2];
        this.dXYZZ=array2[3];
        this.dLJQZ=array2[4];
        this.dJWSR=array2[5];
        this.dLJZY=array2[6];
        this.dLJSW=array2[7];
    }

    public EpidemicNumber(Date date, int[] array1, int[] array2) {
        this.date = date;
        this.XYQZ=array1[0];
        this.WZZ=array1[1];
        this.XYYS=array1[2];
        this.XYZZ=array1[3];
        this.LJQZ=array1[4];
        this.JWSR=array1[5];
        this.LJZY=array1[6];
        this.LJSW=array1[7];
        this.dXYQZ=array2[0];
        this.dWZZ=array2[1];
        this.dXYYS=array2[2];
        this.dXYZZ=array2[3];
        this.dLJQZ=array2[4];
        this.dJWSR=array2[5];
        this.dLJZY=array2[6];
        this.dLJSW=array2[7];
    }

    public EpidemicNumber(Date date, int XYQZ, int WZZ, int XYYS, int XYZZ, int LJQZ, int JWSR, int LJZY, int LJSW, int dXYQZ, int dWZZ, int dXYYS, int dXYZZ, int dLJQZ, int dJWSR, int dLJZY, int dLJSW) {
        this.date = date;
        this.XYQZ = XYQZ;
        this.WZZ = WZZ;
        this.XYYS = XYYS;
        this.XYZZ = XYZZ;
        this.LJQZ = LJQZ;
        this.JWSR = JWSR;
        this.LJZY = LJZY;
        this.LJSW = LJSW;
        this.dXYQZ = dXYQZ;
        this.dWZZ = dWZZ;
        this.dXYYS = dXYYS;
        this.dXYZZ = dXYZZ;
        this.dLJQZ = dLJQZ;
        this.dJWSR = dJWSR;
        this.dLJZY = dLJZY;
        this.dLJSW = dLJSW;
    }

    public void setXYQZ(int XYQZ) {
        this.XYQZ = XYQZ;
    }

    public void setWZZ(int WZZ) {
        this.WZZ = WZZ;
    }

    public void setXYYS(int XYYS) {
        this.XYYS = XYYS;
    }

    public void setXYZZ(int XYZZ) {
        this.XYZZ = XYZZ;
    }

    public void setLJQZ(int LJQZ) {
        this.LJQZ = LJQZ;
    }

    public void setJWSR(int JWSR) {
        this.JWSR = JWSR;
    }

    public void setLJZY(int LJZY) {
        this.LJZY = LJZY;
    }

    public void setLJSW(int LJSW) {
        this.LJSW = LJSW;
    }

    public Date getDate() {
        return date;
    }

    public int getXYQZ() {
        return XYQZ;
    }

    public int getWZZ() {
        return WZZ;
    }

    public int getXYYS() {
        return XYYS;
    }

    public int getXYZZ() {
        return XYZZ;
    }

    public int getLJQZ() {
        return LJQZ;
    }

    public int getJWSR() {
        return JWSR;
    }

    public int getLJZY() {
        return LJZY;
    }

    public int getLJSW() {
        return LJSW;
    }

    public int getdXYQZ() {
        return dXYQZ;
    }

    public int getdWZZ() {
        return dWZZ;
    }

    public int getdXYYS() {
        return dXYYS;
    }

    public int getdXYZZ() {
        return dXYZZ;
    }

    public int getdLJQZ() {
        return dLJQZ;
    }

    public int getdJWSR() {
        return dJWSR;
    }

    public int getdLJZY() {
        return dLJZY;
    }

    public int getdLJSW() {
        return dLJSW;
    }
}
