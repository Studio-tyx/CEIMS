package edu.njust.mapper;

import edu.njust.entity.Tour;

import java.util.List;

public interface TourMapper {
    List<Tour> selectTourByOutID(int orID);
    int insertTour(Tour tour);
    List<String> selectAllUser();
}
