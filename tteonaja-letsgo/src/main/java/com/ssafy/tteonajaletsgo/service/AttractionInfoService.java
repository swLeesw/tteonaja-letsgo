package com.ssafy.tteonajaletsgo.service;


import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.dto.AttractionInfoAndDescription;
import com.ssafy.tteonajaletsgo.dto.attractionInfo.AttractionInfoCheckDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AttractionInfoService {
    List<AttractionInfoAndDescription> getRegion(Map<String, String> map) throws SQLException;
    List<Sido> getSido() throws SQLException;
    List<Gugun> getGugun(int sidoCode) throws SQLException;
    List<AttractionInfoAndDescription> getTopAttraction() throws Exception;
    void likeInfo(int id) throws Exception;
    void likeCancelInfo(int id) throws Exception;
    boolean checkInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception;
    void insertCheckInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception;
    void deleteCheckInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws  Exception;

}