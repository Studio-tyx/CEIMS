package edu.njust.service;

import edu.njust.dao.CampusNumDAO;
import edu.njust.dao.NationalNumDAO;
import edu.njust.entity.EpidemicNumber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

public class NumberService {

    /**
     * 更新全国数据
     * 先比对数据库最新日期是否是今天 如果是今天 则爬虫更新数据 如果不是 则添加数据
     * @return boolean 更新操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean updateNational() throws IOException {
        boolean res=false;
        NationalNumDAO nationalNumDAO=new NationalNumDAO();
        Date today=new Date(System.currentTimeMillis());
        Date newest=nationalNumDAO.getNewestDate();
        String todayString=today.toString();
        String newestString=newest.toString();
        boolean isToday=todayString.equalsIgnoreCase(newestString);

        String url="https://www.baidu.com/s?wd=%E7%96%AB%E6%83%85";
        Document document = Jsoup.parse(new URL(url), 30000);
        Elements elements = document.getElementsByClass("total virus-item-text");
        int[] array1 =new int[8];
        for(int i=0;i<elements.size();i++){
            array1[i]=Integer.parseInt(elements.get(i).text());
        }
        int[] array2 =new int[8];
        elements=document.getElementsByClass("diff virus-item-text");
        for(int i=0;i<elements.size();i++){
            array2[i]=Integer.parseInt(elements.get(i).select("span").text());
        }
        EpidemicNumber epidemicNumber =new EpidemicNumber(today,array1,array2);

        if(isToday){
            EpidemicNumber newestNum=nationalNumDAO.getNumByDate(today);
            if(!epidemicNumber.equals(newestNum)){
                res=nationalNumDAO.updateNum(epidemicNumber);
            }
        }else{
            res=nationalNumDAO.addNum(epidemicNumber);
        }
        System.out.println("NumberService.updateNational:Have"+(res?" ":" not ")+"updated national number!");
        return res;
    }

    /**
     * 获取数据库中最新全国记录
     * @return EpidemicNumber 全国数据封装
     * @throws IOException 数据库读写错误
     */
    public EpidemicNumber getCurrentNational() throws IOException {
        Date today=new Date(System.currentTimeMillis());
        EpidemicNumber res=new NationalNumDAO().getNumByDate(today);
        System.out.println("NumberService.updateNational:Have found newest national "+res.show());
        return res;
    }

    /**
     * 增加全校数据（先比对今日数据是否已存在 再增加记录）
     * @param epidemicNumber EpidemicNumber学校数据封装
     * @return boolean 增加操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean addCampus(EpidemicNumber epidemicNumber) throws IOException {
        boolean res=false;
        if(epidemicNumber!=null) {
            CampusNumDAO campusNumDAO = new CampusNumDAO();
            if (!campusNumDAO.hasRegister(epidemicNumber.getDate())) {
                res = campusNumDAO.addCampusNum(epidemicNumber);
            }
            System.out.println("NumberService.updateNational:Have" + (res ? " " : " not ") + "added " + epidemicNumber.show());
        }
        return res;
    }

    /**
     * 今日是否已存在全校数据
     * @return boolean 存在与否
     * @throws IOException 数据库读写错误
     */
    public boolean hasTodayCampus() throws IOException {
        boolean res=new CampusNumDAO().hasRegister(new Date(System.currentTimeMillis()));
        System.out.println("NumberService.updateNational:Today have"+(res?" ":" not ")+"campus number!");
        return res;
    }

    /**
     * 更新全校数据
     * @param epidemicNumber EpidemicNumber学校数据封装
     * @return boolean 增加操作成败
     * @throws IOException 数据库读写错误
     */
    public boolean updateCampus(EpidemicNumber epidemicNumber) throws IOException {
        boolean res=false;
        if(epidemicNumber!=null){
            res=new CampusNumDAO().updateCampus(epidemicNumber);
            System.out.println("NumberService.updateCampus:Have" + (res ? " " : " not ") + "updated " + epidemicNumber.show());
        }
        return res;
    }

    /**
     * 根据日期获取学校数据
     * @param date Date日期
     * @return EpidemicNumber 学校数据封装
     * @throws IOException 数据库读写错误
     */
    public EpidemicNumber getCampus(Date date) throws IOException {
        EpidemicNumber res=null;
        if(date!=null){
            res=new CampusNumDAO().getCampusByDate(date);
            if(res!=null)
                System.out.println("NumberService.getCampus:Have found number:"+ res.show());
        }
        return res;
    }

}
