package com.ssafy.tteonajaletsgo.service;


import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.dto.AttractionInfoAndDescription;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AttractionInfoService {
    List<AttractionInfoAndDescription> getRegion(Map<String, String> map) throws SQLException;
    List<Sido> getSido() throws SQLException;
    List<Gugun> getGugun(int sidoCode) throws SQLException;
    List<AttractionInfo> getTopAttraction() throws Exception;
}