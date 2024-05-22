package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseUpdateDto;
import com.ssafy.tteonajaletsgo.mapper.TravelCourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TravelCourseServiceImpl implements TravelCourseService{

    private final TravelCourseMapper travelCourseMapper;

    @Override
    public TravelCourse getArticle(int articleNo) throws Exception {
        return travelCourseMapper.getArticle(articleNo);
    }


    @Override
    public TravelCourseListDto listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "9" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "user_id");
        List<TravelCourse> list = travelCourseMapper.listArticle(param);

        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "user_id");
        int totalArticleCount = travelCourseMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        TravelCourseListDto travelCourseListDto = new TravelCourseListDto();
        travelCourseListDto.setArticles(list);
        travelCourseListDto.setCurrentPage(currentPage);
        travelCourseListDto.setTotalPageCount(totalPageCount);

        return travelCourseListDto;
    }

    @Override
    public void registArticle(TravelCourseSaveDto travelCourseSaveDto) throws Exception {
        travelCourseMapper.registArticle(travelCourseSaveDto);
    }


    @Override
    public void deleteArticle(int articleNo) throws Exception {
        travelCourseMapper.deleteArticle(articleNo);
    }

    @Override
    public void updateHit(int articleNo) throws Exception {
        travelCourseMapper.updateHit(articleNo);
    }

    @Override
    public void modifyArticle(TravelCourseUpdateDto travelCourseUpdateDto) throws Exception {
        travelCourseMapper.modifyArticle(travelCourseUpdateDto);
    }
}
