package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.TravelCourse;
import com.ssafy.tteonajaletsgo.dto.freeBoard.FreeBoardUpdateDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseListDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseSaveDto;
import com.ssafy.tteonajaletsgo.dto.travelCourse.TravelCourseUpdateDto;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.TravelCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tteonaja/api/board/travel")
public class TravelCourseController {

    private final TravelCourseService travelCourseService;

    @PostMapping("/regist")
    public ResponseEntity<?> registArticle(@Validated @RequestBody TravelCourseSaveDto travelCourseSaveDto) {
        //글 등록 작업
        try {
            travelCourseService.registArticle(travelCourseSaveDto);
        } catch (Exception e) {
            log.info("registerError={}", e);
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

    @PutMapping
    public ResponseEntity<String> modifyArticle(@RequestBody(required = true)TravelCourseUpdateDto travelCourseUpdateDto) {
        try {
            travelCourseService.modifyArticle(travelCourseUpdateDto);
        } catch (Exception e) {
            return new ResponseEntity<String>("modifyError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }


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
