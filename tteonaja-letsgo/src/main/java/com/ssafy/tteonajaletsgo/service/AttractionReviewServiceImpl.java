package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewCheckDto;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;
import com.ssafy.tteonajaletsgo.mapper.AttractionReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionReviewServiceImpl implements AttractionReviewService{

    private final AttractionReviewMapper attractionReviewMapper;

    @Override
    public List<AttractionReview> listReview(int attractionId) throws Exception {
        return attractionReviewMapper.listReview(attractionId);
    }

    @Override
    public void updateHit(int reviewNo) throws Exception {
        attractionReviewMapper.updateHit(reviewNo);
    }

    @Override
    public void registReview(AttractionReviewSaveDto attractionReviewSaveDto) throws Exception {
        attractionReviewMapper.registReview(attractionReviewSaveDto);
    }

    @Override
    public void likeReview(int reviewNo) throws Exception {
        attractionReviewMapper.likeReview(reviewNo);
    }

    public void likeCancelReview(int reviewNo) throws Exception {
        attractionReviewMapper.likeCancelReview(reviewNo);
    }


    public boolean checkReview(AttractionReviewCheckDto attractionReviewCheckDto) throws Exception {
        return attractionReviewMapper.checkReview(attractionReviewCheckDto) != null;
    }

    @Override
    public void insertCheckReview(AttractionReviewCheckDto attractionReviewCheckDto) throws Exception {
        attractionReviewMapper.insertCheckReview(attractionReviewCheckDto);
    }

    @Override
    public void deleteCheckReview(AttractionReviewCheckDto attractionReviewCheckDto) throws Exception {
        attractionReviewMapper.deleteCheckReview(attractionReviewCheckDto);
    }
}