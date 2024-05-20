package com.ssafy.tteonajaletsgo.dto.freeBoard;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class FreeBoardListDto {
    
    //글 목록
    private List<FreeBoard> articles;
    //현재 페이지 번호
    private int currentPage;
    //전체 페이지 수
    private int totalPageCount;
}
