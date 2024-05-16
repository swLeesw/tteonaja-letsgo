package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;

import java.util.List;

public interface FreeCommentService {
    void registComment(FreeCommentSaveDto freeCommentSaveDto) throws Exception;
    List<FreeComment> listComment(int articleNo) throws Exception;
    void deleteComment(int commentNo) throws Exception;
}
