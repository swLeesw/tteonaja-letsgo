package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;

import java.util.List;

public interface AttractionReviewService {
    List<AttractionReview> listReview(int attractionId) throws Exception;
    void updateHit(int reviewNo) throws Exception;
    void registReview(AttractionReviewSaveDto attractionReviewSaveDto) throws Exception;
}
