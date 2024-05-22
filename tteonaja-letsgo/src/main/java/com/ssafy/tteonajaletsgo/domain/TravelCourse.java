package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class TravelCourse {
    int articleNo;
    String userId;
    String subject;
    String content;
    int hit;
    String registerTime;
    int courseLike;
}
