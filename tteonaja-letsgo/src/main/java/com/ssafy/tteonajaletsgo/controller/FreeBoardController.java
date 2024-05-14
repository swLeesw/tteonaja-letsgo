package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import com.ssafy.tteonajaletsgo.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board/free")
public class FreeBoardController {

    FreeBoardService freeBoardService;

    @PostMapping("/regist")
    public ResponseEntity<?> registArticle(@Validated @RequestBody FreeBoardSaveDto freeBoardSaveDto) throws Exception {
        //글 등록 작업
        freeBoardService.registArticle(freeBoardSaveDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<FreeBoardListDto> listArticle() throws Exception {
        return new ResponseEntity<FreeBoardListDto>(freeBoardService.listArticle(), HttpStatus.OK);
    }

    @GetMapping("/{articleno}")
    public ResponseEntity<FreeBoard> getArticle(
            @PathVariable(value = "articleno", required = true) int articleNo
    ) throws Exception {
//        freeBoardService.updateHit(articleno);
        return new ResponseEntity<FreeBoard>(freeBoardService.getArticle(articleNo), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyArticle(@RequestBody(required = true) FreeBoardUpdateDto freeBoardUpdateDto) throws Exception {
        freeBoardService.modifyArticle(freeBoardUpdateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(@PathVariable(value = "articleno", required = true) int articleno) throws Exception {
        freeBoardService.deleteArticle(articleno);
        return ResponseEntity.ok().build();
    }
}
