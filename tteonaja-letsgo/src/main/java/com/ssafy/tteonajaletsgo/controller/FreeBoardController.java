package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.FreeBoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("tteonaja/api/board/free")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @Operation(summary = "게시글 등록", description = "게시글을 등록한다")
    @PostMapping("/regist")
    public ResponseEntity<?> registArticle(@Validated @RequestBody FreeBoardSaveDto freeBoardSaveDto) {
        //글 등록 작업
        try {
        freeBoardService.registArticle(freeBoardSaveDto);
        } catch (Exception e) {
            return new ResponseEntity<String>("registError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @Operation(summary = "게시글 불러오기", description = "게시글 리스트를 불러온다.")
    @GetMapping("/list")
    public ResponseEntity<?> listArticle(
            @RequestParam @Parameter(description = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map
    ) {
//        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
//        int start = currentPage * sizePerPage - sizePerPage;
        try {
            return new ResponseEntity<FreeBoardListDto>(freeBoardService.listArticle(map), HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    @Operation(summary = "게시글 상세정보", description = "게시글 상세 정보를 불러온다")
    @GetMapping("/{articleno}")
    public ResponseEntity<FreeBoard> getArticle(
            @PathVariable(value = "articleno", required = true) int articleNo
    ) throws Exception {
        freeBoardService.updateHit(articleNo);
        return new ResponseEntity<FreeBoard>(freeBoardService.getArticle(articleNo), HttpStatus.OK);
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정한다")
    @PutMapping
    public ResponseEntity<String> modifyArticle(@RequestBody(required = true) FreeBoardUpdateDto freeBoardUpdateDto) {
        System.out.println(freeBoardUpdateDto.toString());
        try {
        freeBoardService.modifyArticle(freeBoardUpdateDto);
        } catch (Exception e) {
            return new ResponseEntity<String>("modifyError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제한다")
    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(@PathVariable(value = "articleno", required = true) int articleno) throws Exception {
        freeBoardService.deleteArticle(articleno);
        return ResponseEntity.ok().build();
    }
}
