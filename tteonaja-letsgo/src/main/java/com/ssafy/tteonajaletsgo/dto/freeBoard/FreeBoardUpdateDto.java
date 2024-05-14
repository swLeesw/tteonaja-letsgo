package com.ssafy.tteonajaletsgo.dto.freeBoard;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FreeBoardUpdateDto {
    @NotNull
    private int articleNo;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
