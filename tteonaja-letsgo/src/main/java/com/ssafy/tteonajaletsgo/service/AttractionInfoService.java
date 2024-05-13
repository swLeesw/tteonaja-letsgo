package com.ssafy.tteonajaletsgo.service;


import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AttractionInfoService {
    List<AttractionInfo> getRegion(Map<String, String> map) throws SQLException;
    List<Sido> getSidoCode() throws SQLException;
    List<Gugun> getGugun(int sidoCode) throws SQLException;
}
