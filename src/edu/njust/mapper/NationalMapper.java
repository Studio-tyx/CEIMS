package edu.njust.mapper;

import edu.njust.entity.EpidemicNumber;

import java.sql.Date;

public interface NationalMapper {
    int insertNationalNum(EpidemicNumber epidemicNumber);
    int updateNationalByDate(EpidemicNumber epidemicNumber);
    Date selectNewest();
    EpidemicNumber selectNumByDate(Date date);
}
