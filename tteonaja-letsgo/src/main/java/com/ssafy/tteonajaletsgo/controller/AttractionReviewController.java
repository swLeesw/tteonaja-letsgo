package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewCheckDto;
import com.ssafy.tteonajaletsgo.dto.attractionReview.AttractionReviewSaveDto;
import com.ssafy.tteonajaletsgo.dto.common.CheckDto;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.AttractionReviewService;
import io.swagger.models.Response;
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


    @PostMapping("/regist")
    public ResponseEntity<?> registReview(@Validated @RequestBody AttractionReviewSaveDto attractionReviewSaveDto) {

        try {
            attractionReviewService.registReview(attractionReviewSaveDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            log.info("reviewRegist error = {}", e);
            return ExceptionResponse.response(e);
        }

    }

    @GetMapping("/list/{attractionid}")
    public ResponseEntity<?> listReview(@PathVariable(value = "attractionid", required = true) int attractionid) {

        try {
            List<AttractionReview> list = attractionReviewService.listReview(attractionid);
            return new ResponseEntity<List<AttractionReview>>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.info("listReivewerror = {}", e);
            return ExceptionResponse.response(e);
        }

    }

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
            log.info("likeError = {}", e);
            return ExceptionResponse.response(e);
        }

    }



//    @GetMapping("/{reviewno}")
//    public ResponseEntity<String> modifyReview() {
//
//    }
//
//    @DeleteMapping("/{reviewno}")
//    public ResponseEntity<String> deleteReview() {
//
//    }


}
