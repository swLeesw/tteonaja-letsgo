package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class TravelComment {
    private int commentNo;
    private int articleNo;
    private String userId;
    private String content;
    private String registerTime;
}
