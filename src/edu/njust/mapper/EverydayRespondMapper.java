package edu.njust.mapper;

import edu.njust.entity.EverydayRespond;

import java.sql.Timestamp;
import java.util.List;

public interface EverydayRespondMapper {
    int insertEverydayRespond(EverydayRespond erp);
    List<EverydayRespond> selectMsgByID(String id);
}
