package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.service.AttractionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tteonaja/api/attractionInfo/")
@RequiredArgsConstructor
public class AttractionInfoController {

    private final AttractionInfoService attractionInfoService;

    @GetMapping("/region")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<AttractionInfo> region(@RequestParam Map<String, String> map) throws SQLException {
        List<AttractionInfo> list = attractionInfoService.getRegion(map);
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

}
