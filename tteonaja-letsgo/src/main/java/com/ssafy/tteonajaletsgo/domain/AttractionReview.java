package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class AttractionReview {

    private int reviewNo;
    private String userId;
    private String reviewTitle;
    private String content;
    private int hit;
    private String registerTime;
    private int reviewLike;
    private String attractionId;
}
