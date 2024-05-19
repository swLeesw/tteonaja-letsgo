package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import com.ssafy.tteonajaletsgo.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

    private final FreeBoardMapper freeBoardMapper;

    @Override
    public void registArticle(FreeBoardSaveDto freeBoardSaveDto) throws Exception {
        freeBoardMapper.registArticle(freeBoardSaveDto);
    }

    @Override
    public FreeBoardListDto listArticle(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "user_id");
        List<FreeBoard> list = freeBoardMapper.listArticle(param);

        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "user_id");
        int totalArticleCount = freeBoardMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        FreeBoardListDto boardListDto = new FreeBoardListDto();
        boardListDto.setArticles(list);
        boardListDto.setCurrentPage(currentPage);
        boardListDto.setTotalPageCount(totalPageCount);

        return boardListDto;
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
