package com.ssafy.tteonajaletsgo.dto.freeComment;

import lombok.Data;

@Data
public class FreeCommentSaveDto {
    private int articleNo;
    private String userId;
    private String content;
}
