package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import com.ssafy.tteonajaletsgo.mapper.FreeCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeCommentServiceImpl implements FreeCommentService {

    private final FreeCommentMapper freeCommentMapper;

    @Override
    public void registComment(FreeCommentSaveDto freeCommentSaveDto) throws Exception {
        freeCommentMapper.registComment(freeCommentSaveDto);
    }

    @Override
    public List<FreeComment> listComment(int articleNo) throws Exception {
        return freeCommentMapper.listComment(articleNo);
    }

    @Override
    public void deleteComment(int commentNo) throws Exception {
        freeCommentMapper.deleteComment(commentNo);
    }
}
