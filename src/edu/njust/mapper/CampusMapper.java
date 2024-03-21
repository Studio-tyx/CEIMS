package edu.njust.mapper;

import edu.njust.entity.EpidemicNumber;

import java.sql.Date;

public interface CampusMapper {
    int insertCampus(EpidemicNumber epidemicNumber);
    int updateCampus(EpidemicNumber epidemicNumber);
    EpidemicNumber selectByDate(Date date);
}
