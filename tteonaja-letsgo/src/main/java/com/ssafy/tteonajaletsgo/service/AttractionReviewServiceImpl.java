package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;
import com.ssafy.tteonajaletsgo.mapper.AttractionReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}