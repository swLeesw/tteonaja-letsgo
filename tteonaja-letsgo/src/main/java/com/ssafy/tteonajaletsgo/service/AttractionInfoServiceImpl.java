package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.mapper.AttractionInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttractionInfoServiceImpl implements AttractionInfoService{

    private final AttractionInfoMapper attractionInfoMapper;

    @Override
    public List<AttractionInfo> getRegion(Map<String, String> map) throws SQLException {
        return attractionInfoMapper.getRegion(map);
    }

    @Override
    public List<Sido> getSido() throws SQLException {
        return attractionInfoMapper.getSido();
    }

    @Override
    public List<Gugun> getGugun(int sidoCode) throws SQLException {
        return attractionInfoMapper.getGugun(sidoCode);
    }
}