package com.ssafy.tteonajaletsgo.dto.freeBoard;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import lombok.Data;

import java.util.List;

@Data
public class FreeBoardListDto {

    private List<FreeBoard> articles;
    private int currentPage;
    private int totalPageCount;
}
