package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewCheckDto;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;
import com.ssafy.tteonajaletsgo.dto.common.CheckDto;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.AttractionReviewService;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("tteonaja/api/review")
public class AttractionReviewController {

    private final AttractionReviewService attractionReviewService;

    @Operation(summary = "리뷰 등록", description = "리뷰를 등록한다")
    @PostMapping("/regist")
    public ResponseEntity<?> registReview(@Validated @RequestBody AttractionReviewSaveDto attractionReviewSaveDto) {

        try {
            attractionReviewService.registReview(attractionReviewSaveDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("reviewRegist error = {}", e);
            return ExceptionResponse.response(e);
        }

    }
    @Operation(summary = "리뷰 목록", description = "리뷰 목록을 받아온다")
    @GetMapping("/list/{attractionid}")
    public ResponseEntity<?> listReview(@PathVariable(value = "attractionid", required = true) int attractionid) {

        try {
            List<AttractionReview> list = attractionReviewService.listReview(attractionid);
            return new ResponseEntity<List<AttractionReview>>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("listReivewerror = {}", e);
            return ExceptionResponse.response(e);
        }

    }
    @Operation(summary = "리뷰 좋아요", description = "아이디당 리뷰 좋아요 및 좋아요 취소를 한다.")
    @GetMapping("/like/{reviewno}/{userid}")
    public ResponseEntity<?> likeReview(@PathVariable(value = "reviewno", required = true) int reviewno,
                                        @PathVariable(value = "userid", required = true) String userid) {

        try {
            AttractionReviewCheckDto attractionReviewCheckDto = new AttractionReviewCheckDto();
            attractionReviewCheckDto.setReviewNo(reviewno);
            attractionReviewCheckDto.setUserId(userid);
            CheckDto checkDto = new CheckDto();
            if (attractionReviewService.checkReview(attractionReviewCheckDto)) {
                attractionReviewService.likeCancelReview(reviewno);
                attractionReviewService.deleteCheckReview(attractionReviewCheckDto);
                checkDto.setCheck(true);
            } else {
                attractionReviewService.likeReview(reviewno);
                attractionReviewService.insertCheckReview(attractionReviewCheckDto);
                checkDto.setCheck(false);
            }
            return new ResponseEntity<CheckDto>(checkDto, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("reviewLikeError = {}", e);
            return ExceptionResponse.response(e);
        }
    }
    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제한다.")
    @DeleteMapping("/{reviewno}")
    public ResponseEntity<?> deleteReview(@PathVariable(value = "reviewno", required = true) int reviewno) {

        try {
            attractionReviewService.deleteReview(reviewno);
            log.debug("success reviewDelete");
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("reviewDeleteError = {}", e);
            return ExceptionResponse.response(e);
        }

    }
    @Operation(summary = "top5리뷰", description = "리뷰 좋아요순 -> 리뷰 등록 날짜 순으로 최대 5개의 리스트를 뽑아온다.")
    @GetMapping("/top")
    public ResponseEntity<?> getTopReview() {
        try {
            List<AttractionReview> topReviews = attractionReviewService.getTopReview();
            log.debug("success get top");
            if (topReviews == null) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<List<AttractionReview>>(topReviews, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }


}
