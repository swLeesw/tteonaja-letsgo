package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface TravelCourseMapper {

    void registArticle(TravelCourseSaveDto travelCourseSaveDto) throws Exception;
    List<TravelCourse> listArticle(Map<String, Object> param) throws Exception;
    TravelCourse getArticle(int articleNo) throws Exception;
    void deleteArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    void modifyArticle(TravelCourseUpdateDto travelCourseUpdateDto) throws Exception;
}
