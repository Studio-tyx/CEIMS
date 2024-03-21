package edu.njust.mapper;

import edu.njust.entity.OutReport;

import java.util.List;
import java.sql.Date;

public interface OutReportMapper {
    int insertOutReport(OutReport or);
    int selectNotBackByID(int orID);
    int selectReportIDByID(String id);
    List<OutReport> selectUser(String id);
    String selectIDByRID(int outID);
    List<OutReport> selectReportIDNotBack();
    int updateActualBackDate(int param1, Date param2);
    List<OutReport> selectAbnormal();
}
