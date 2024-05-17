package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.FreeComment;
import com.ssafy.tteonajaletsgo.dto.freeComment.FreeCommentSaveDto;
import com.ssafy.tteonajaletsgo.service.FreeCommentService;
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

    @PostMapping("/regist")
    public ResponseEntity<?> registComment(@RequestBody FreeCommentSaveDto freeCommentSaveDto) {
        try {
            freeCommentService.registComment(freeCommentSaveDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("registError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{articleno}")
    public ResponseEntity<List<FreeComment>> listComment(@PathVariable(value = "articleno", required = true) int articleno) throws Exception{
        return new ResponseEntity<>(freeCommentService.listComment(articleno), HttpStatus.OK);
    }

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
