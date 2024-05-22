package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseUpdateDto;

import java.util.Map;

public interface TravelCourseService {
    TravelCourse getArticle(int articleNo) throws Exception;
    TravelCourseListDto listArticle(Map<String, String> map) throws Exception;
    void registArticle(TravelCourseSaveDto travelCourseSaveDto) throws Exception;
    void deleteArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;
    void modifyArticle(TravelCourseUpdateDto travelCourseUpdateDto) throws Exception;
}
