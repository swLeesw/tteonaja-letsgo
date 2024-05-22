package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
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

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/tteonaja/api/board/travel")
public class TravelCourseController {

    private final TravelCourseService travelCourseService;
    @Operation(summary = "여행코스 등록", description = "여행코스를 등록한다.")
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
    @Operation(summary = "여행코스 리스트", description = "여행코스 리스트를 불러온다")
    @GetMapping("/list")
    public ResponseEntity<?> listArticle(@RequestParam @Parameter(required = true) Map<String, String> map) {
        try {

            return new ResponseEntity<TravelCourseListDto>(travelCourseService.listArticle(map), HttpStatus.OK);

        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }
    @Operation(summary = "여행코스 조회", description = "여행코스를 조회한다.")
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
    @Operation(summary = "여행코스 수정", description = "여행코스를 수정한다.")
    @PutMapping
    public ResponseEntity<String> modifyArticle(@RequestBody(required = true)TravelCourseUpdateDto travelCourseUpdateDto) {
        try {
            travelCourseService.modifyArticle(travelCourseUpdateDto);
        } catch (Exception e) {
            return new ResponseEntity<String>("modifyError", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "여행코스 삭제", description = "여행코스를 삭제한다.")
    @DeleteMapping("/{articleno}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "articleno", required = true) int articleno) throws Exception {
        try {
            travelCourseService.deleteArticle(articleno);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    @Operation(summary = "top5여행코스", description = "top5여행 코스를 뽑아온다")
    @GetMapping("/top")
    public ResponseEntity<?> getTopReview() {
        try {
            List<TravelCourse> topReviews = travelCourseService.getTop();
            log.debug("success get top");
            if (topReviews == null) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<List<TravelCourse>>(topReviews, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("travelCourseTop5Error = {}", e);
            return ExceptionResponse.response(e);
        }
    }



}
