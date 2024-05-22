package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.domain.TravelComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelComment.TravelCommentSaveDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TravelCommentMapper {

    void registComment(TravelCommentSaveDto travelCommentSaveDto) throws Exception;
    List<TravelComment> listComment(int articleNo) throws Exception;
    void deleteComment(int commentNo) throws Exception;
}
