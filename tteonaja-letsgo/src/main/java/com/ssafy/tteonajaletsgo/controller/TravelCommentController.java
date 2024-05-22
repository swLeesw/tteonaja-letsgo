package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.TravelComment;
import com.ssafy.tteonajaletsgo.dto.travelComment.TravelCommentSaveDto;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.TravelCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tteonaja/api/board/travel-comment")
public class TravelCommentController {

    private final TravelCommentService travelCommentService;

    @Operation(summary = "댓글 저장", description = "게시글별 댓글을 저장한다")
    @PostMapping("/regist")
    public ResponseEntity<?> registComment(@RequestBody TravelCommentSaveDto travelCommentSaveDto) {
        try {
            travelCommentService.registComment(travelCommentSaveDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("registError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "게시글별 댓글 불러오기", description = "게시글별 댓글을 불러온다")
    @GetMapping("/list/{articleno}")
    public ResponseEntity<?> listComment(@PathVariable(value = "articleno", required = true) int articleno) {
        try {
            return new ResponseEntity<List<TravelComment>>(travelCommentService.listComment(articleno), HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제한다")
    @DeleteMapping("/{commentno}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "commentno", required = true) int commentno) {
        try {
            travelCommentService.deleteComment(commentno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<String>("deleteError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
