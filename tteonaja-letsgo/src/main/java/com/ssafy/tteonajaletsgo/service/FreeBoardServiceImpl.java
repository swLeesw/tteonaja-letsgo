package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import com.ssafy.tteonajaletsgo.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

    private final FreeBoardMapper freeBoardMapper;

    @Override
    public void registArticle(FreeBoardSaveDto freeBoardSaveDto) throws Exception {
        freeBoardMapper.registArticle(freeBoardSaveDto);
    }

    @Override
    public FreeBoardListDto listArticle() throws Exception {
        FreeBoardListDto freeBoardListDto = new FreeBoardListDto();
        List<FreeBoard> articles = freeBoardMapper.listArticle();

        freeBoardListDto.setArticles(articles);
        freeBoardListDto.setCurrentPage(0);
        freeBoardListDto.setTotalPageCount(articles.size());
        return freeBoardListDto;
    }

    @Override
    public FreeBoard getArticle(int articleNo) throws Exception {
        return freeBoardMapper.getArticle(articleNo);
    }

    @Override
    public void modifyArticle(FreeBoardUpdateDto freeBoardUpdateDto) throws Exception {
        freeBoardMapper.modifyArticle(freeBoardUpdateDto);
    }

    @Override
    public void deleteArticle(int articleNo) throws Exception {
        freeBoardMapper.deleteArticle(articleNo);
    }

    @Override
    public void updateHit(int articleNo) throws Exception {
        freeBoardMapper.updateHit(articleNo);
    }
}
