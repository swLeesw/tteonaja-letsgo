package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;

import java.util.List;

public interface FreeBoardService {
    void registArticle(FreeBoardSaveDto freeBoardSaveDto) throws Exception;
    FreeBoardListDto listArticle() throws Exception;
    FreeBoard getArticle(int articleNo) throws Exception;
    void modifyArticle(FreeBoardUpdateDto freeBoardUpdateDto) throws Exception;
    void deleteArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;
}
