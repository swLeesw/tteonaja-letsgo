package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeCommentMapper {
    void registComment(FreeCommentSaveDto freeCommentSaveDto) throws Exception;
    List<FreeComment> listComment(int articleNo) throws Exception;
    void deleteComment(int commentNo) throws Exception;
}

