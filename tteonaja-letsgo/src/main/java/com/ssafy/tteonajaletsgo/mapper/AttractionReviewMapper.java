package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionReviewMapper {
    List<AttractionReview> listReview(int attractionId) throws Exception;
    void updateHit(int reviewNo) throws Exception;
    void registReview(AttractionReviewSaveDto attractionReviewSaveDto) throws Exception;
}

