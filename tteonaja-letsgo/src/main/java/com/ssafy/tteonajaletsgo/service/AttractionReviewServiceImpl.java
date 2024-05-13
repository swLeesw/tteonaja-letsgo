package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.mapper.AttractionReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttractionReviewServiceImpl implements AttractionReviewService{

    private final AttractionReviewMapper attractionReviewMapper;

}