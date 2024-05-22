package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.domain.TravelComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelComment.TravelCommentSaveDto;
import com.ssafy.tteonajaletsgo.mapper.TravelCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelCommentServiceImpl implements TravelCommentService {

    private final TravelCommentMapper travelCommentMapper;

    @Override
    public void registComment(TravelCommentSaveDto travelCommentSaveDto) throws Exception {
        travelCommentMapper.registComment(travelCommentSaveDto);
    }

    @Override
    public List<TravelComment> listComment(int articleNo) throws Exception {
        return travelCommentMapper.listComment(articleNo);
    }

    @Override
    public void deleteComment(int commentNo) throws Exception {
        travelCommentMapper.deleteComment(commentNo);
    }

}
