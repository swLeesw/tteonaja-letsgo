package com.ssafy.tteonajaletsgo.dto.travelCourse;

import lombok.Data;

import java.util.List;

@Data
public class TravelCourseListSaveDto {
    private String articleNo;
    private List<Integer> attractionList;
}
