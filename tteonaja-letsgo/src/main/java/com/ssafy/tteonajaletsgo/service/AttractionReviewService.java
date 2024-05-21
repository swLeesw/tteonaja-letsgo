package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewCheckDto;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AttractionReviewService {
    List<AttractionReview> listReview(int attractionId) throws Exception;
    void updateHit(int reviewNo) throws Exception;
    void registReview(AttractionReviewSaveDto attractionReviewSaveDto) throws Exception;
    void likeReview(int reviewNo) throws Exception;
    void likeCancelReview(int reviewNo) throws Exception;
    boolean checkReview(AttractionReviewCheckDto attractionReviewCheckDto) throws Exception;
    void insertCheckReview(AttractionReviewCheckDto attractionReviewCheckDto) throws Exception;
    void deleteCheckReview(AttractionReviewCheckDto attractionReviewCheckDto) throws Exception;
}
