package com.ssafy.tteonajaletsgo.dto.travelComment;

import lombok.Data;

@Data
public class TravelCommentSaveDto {
    private int articleNo;
    private String userId;
    private String content;
}
