package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.FreeBoard;
import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import com.ssafy.tteonajaletsgo.domain.TravelCourseList;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardListDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardSaveDto;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseSaveDto;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.TravelCourseService;
import com.ssafy.tteonajaletsgo.service.TravelCourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tteonaja/api/board/travel")
public class TravelCourseController {

    private final TravelCourseService travelCourseService;

    @PostMapping("/regist")
    public ResponseEntity<?> regist(@Validated @RequestBody TravelCourseSaveDto travelCourseSaveDto) {
        //글 등록 작업
        try {
            travelCourseService.regist(travelCourseSaveDto);
        } catch (Exception e) {
            return new ResponseEntity<String>("registError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/registList")
    public ResponseEntity<?> registList(@Validated @RequestBody TravelCourseListSaveDto travelCourseListSaveDto) {
        //글 등록 작업
        try {
            travelCourseService.registList(travelCourseListSaveDto);
        } catch (Exception e) {
            return new ResponseEntity<String>("registError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listArticle(@RequestParam @Parameter(required = true) Map<String, String> map) {
        try {

            return new ResponseEntity<TravelCourseListDto>(travelCourseService.listArticle(map), HttpStatus.OK);

        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    @GetMapping("/{articleno}")
    public ResponseEntity<?> getArticle(@PathVariable(value = "articleno", required = true) int articleNo)  {

        try {
            TravelCourse travelCourse = travelCourseService.getArticle(articleNo);
            travelCourseService.updateHit(articleNo);
            return new ResponseEntity<TravelCourse>(travelCourse, HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }
    @GetMapping("/course/{articleno}")
    public ResponseEntity<?> getArticleList(@PathVariable(value = "articleno", required = true) int articleNo)  {

        try {
            List<TravelCourseList> travelCourseList = travelCourseService.getArticleList(articleNo);
            return new ResponseEntity<List<TravelCourseList>>(travelCourseList, HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }




//    @PutMapping
//    public ResponseEntity<String> modifyArticle(@RequestBody(required = true) FreeBoardUpdateDto freeBoardUpdateDto) {
//        System.out.println(freeBoardUpdateDto.toString());
//        try {
//            freeBoardService.modifyArticle(freeBoardUpdateDto);
//        } catch (Exception e) {
//            return new ResponseEntity<String>("modifyError", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return ResponseEntity.ok().build();
//    }


    @DeleteMapping("/{articleno}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "articleno", required = true) int articleno) throws Exception {
        try {
            travelCourseService.deleteArticle(articleno);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }



}
