package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FreeBoardMapper {
    void registArticle(FreeBoardSaveDto freeBoardSaveDto) throws Exception;
    List<FreeBoard> listArticle(Map<String, Object> param) throws Exception;
    FreeBoard getArticle(int articleNo) throws Exception;
    void modifyArticle(FreeBoardUpdateDto freeBoardUpdateDto) throws Exception;
    void deleteArticle(int articleNo) throws Exception;
    void updateHit(int articleNo) throws Exception;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    List<FreeBoard> getTop() throws Exception;
}