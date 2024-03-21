package edu.njust.mapper;

import edu.njust.entity.EverydayReport;

import java.sql.Timestamp;
import java.util.List;

public interface EverydayReportMapper {
    int insertEverydayReport(EverydayReport er);
    Timestamp selectSeventhReport(String id);
    int deleteAfterSevenDay(String param1,Timestamp param2);
    Timestamp selectNewestTime(String id);
    List<EverydayReport> selectAll(Timestamp param1,Timestamp param2);
    List<EverydayReport> selectUnanswered();
    int updateTemperature(String param1,Timestamp param2,double param3);
}
