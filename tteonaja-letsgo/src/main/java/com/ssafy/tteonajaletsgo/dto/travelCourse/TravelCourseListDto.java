package com.ssafy.tteonajaletsgo.dto.travelCourse;

import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import lombok.Data;

import java.util.List;

@Data
public class TravelCourseListDto {
    
    //게시글
    private List<TravelCourse> articles;
    //현재 페이지
    private int currentPage;
    //총 페이지
    private int totalPageCount;

}
