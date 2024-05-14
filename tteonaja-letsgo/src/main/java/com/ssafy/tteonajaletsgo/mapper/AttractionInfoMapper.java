package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttractionInfoMapper {
    List<AttractionInfo> getRegion(Map<String, String> map) throws SQLException;
    List<Sido> getSido() throws SQLException;
    List<Gugun> getGugun(int sidoCode) throws SQLException;
}
