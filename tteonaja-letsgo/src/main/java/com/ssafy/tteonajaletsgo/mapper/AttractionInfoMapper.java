package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.AttractionReview;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.dto.AttractionInfoAndDescription;
import com.ssafy.tteonajaletsgo.dto.attractionInfo.AttractionInfoCheckDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionInfoMapper {
    List<AttractionInfoAndDescription> getRegion(Map<String, String> map) throws SQLException;
    AttractionInfoAndDescription getAttraction(int id) throws Exception;
    List<Sido> getSido() throws SQLException;
    List<Gugun> getGugun(int sidoCode) throws SQLException;
    List<AttractionInfoAndDescription> getTopAttraction() throws Exception;
    void likeInfo(int id) throws Exception;
    void likeCancelInfo(int id) throws Exception;
    String checkInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception;
    void insertCheckInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception;
    void deleteCheckInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws  Exception;
}
