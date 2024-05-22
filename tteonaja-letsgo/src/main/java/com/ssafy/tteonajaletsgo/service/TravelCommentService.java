package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.domain.TravelComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelComment.TravelCommentSaveDto;

import java.util.List;

public interface TravelCommentService {
    void registComment(TravelCommentSaveDto travelCommentSaveDto) throws Exception;
    List<TravelComment> listComment(int articleNo) throws Exception;
    void deleteComment(int commentNo) throws Exception;
}
