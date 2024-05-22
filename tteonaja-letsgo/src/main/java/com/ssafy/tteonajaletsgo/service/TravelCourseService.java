package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import com.ssafy.tteonajaletsgo.domain.TravelCourseList;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseSaveDto;

import java.util.List;
import java.util.Map;

public interface TravelCourseService {
    TravelCourse getArticle(int articleNo) throws Exception;
    List<TravelCourseList> getArticleList(int articleNo) throws Exception;
    TravelCourseListDto listArticle(Map<String, String> map) throws Exception;
    void regist(TravelCourseSaveDto travelCourseSaveDto) throws Exception;
    void registList(TravelCourseListSaveDto travelCourseListSaveDto) throws Exception;
    void deleteArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;
}
