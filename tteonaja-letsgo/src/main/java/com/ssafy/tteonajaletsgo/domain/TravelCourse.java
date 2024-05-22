package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class TravelCourse {
    int articleNo;
    String userId;
    String subject;
    String content;
    String travelList;
    int hit;
    String registerTime;
    int courseLike;
    String modifiedDate;
}
