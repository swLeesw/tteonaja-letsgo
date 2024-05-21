package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.dto.AttractionInfoAndDescription;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.AttractionInfoService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("tteonaja/api/attractionInfo/")
@RequiredArgsConstructor
public class AttractionInfoController {

    private final AttractionInfoService attractionInfoService;

    @GetMapping("/region")
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<AttractionInfoAndDescription> getAttraction(@RequestParam Map<String, String> map) throws SQLException {
        log.info("searchTerm = {}", map.get("searchTerm"));
        List<AttractionInfoAndDescription> list = attractionInfoService.getRegion(map);
        return list;
    }

    @GetMapping("/sido")
    public List<Sido> getSido() throws SQLException {

        List<Sido> list = attractionInfoService.getSido();
        return list;
    }

    @GetMapping("/gugun/{sidoCode}")
    public List<Gugun> getGugun(@PathVariable("sidoCode") int sidoCode) throws SQLException {
        List<Gugun> list = attractionInfoService.getGugun(sidoCode);
        return list;
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopAttraction() {

        try {
            List<AttractionInfo> topAttraction = attractionInfoService.getTopAttraction();

            if (topAttraction != null) {
                return new ResponseEntity<List<AttractionInfo>>(topAttraction, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.OK);
            }

        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

}
