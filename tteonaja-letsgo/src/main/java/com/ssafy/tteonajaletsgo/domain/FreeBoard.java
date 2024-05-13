package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class FreeBoard {
    int articleNo;
    String userId;
    String subject;
    String content;
    int hit;
    String registerTime;
}
