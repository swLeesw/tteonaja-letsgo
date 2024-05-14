package com.ssafy.tteonajaletsgo.dto.freeBoard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FreeBoardSaveDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
