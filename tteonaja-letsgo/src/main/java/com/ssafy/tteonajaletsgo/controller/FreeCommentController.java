package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import com.ssafy.tteonajaletsgo.service.FreeCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tteonaja/api/board/free-comment")
public class FreeCommentController {

    private final FreeCommentService freeCommentService;

    @Operation(summary = "댓글 저장", description = "게시글별 댓글을 저장한다")
    @PostMapping("/regist")
    public ResponseEntity<?> registComment(@RequestBody FreeCommentSaveDto freeCommentSaveDto) {
        try {
            freeCommentService.registComment(freeCommentSaveDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("registError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "게시글별 댓글 불러오기", description = "게시글별 댓글을 불러온다")
    @GetMapping("/list/{articleno}")
    public ResponseEntity<List<FreeComment>> listComment(@PathVariable(value = "articleno", required = true) int articleno) throws Exception{
        return new ResponseEntity<>(freeCommentService.listComment(articleno), HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제한다")
    @DeleteMapping("/{commentno}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "commentno", required = true) int commentno) {
        try {
            freeCommentService.deleteComment(commentno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<String>("deleteError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
