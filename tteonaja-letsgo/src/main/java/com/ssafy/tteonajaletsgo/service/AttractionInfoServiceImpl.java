package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.AttractionInfo;
import com.ssafy.tteonajaletsgo.domain.Gugun;
import com.ssafy.tteonajaletsgo.domain.Sido;
import com.ssafy.tteonajaletsgo.dto.AttractionInfoAndDescription;
import com.ssafy.tteonajaletsgo.dto.attractionInfo.AttractionInfoCheckDto;
import com.ssafy.tteonajaletsgo.mapper.AttractionInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class AttractionInfoServiceImpl implements AttractionInfoService{

    private final AttractionInfoMapper attractionInfoMapper;

    @Override
    public List<AttractionInfoAndDescription> getRegion(Map<String, String> map) throws SQLException {
        return attractionInfoMapper.getRegion(map);
    }

    @Override
    public List<AttractionInfoAndDescription> getAdditionalRegion(Map<String, Object> map) throws SQLException {
        map.put("attractionInfoSize", Integer.parseInt((String)map.get("attractionInfoSize")));
        return attractionInfoMapper.getAdditionalRegion(map);
    }

    @Override
    public AttractionInfoAndDescription getAttraction(int id) throws Exception {
        return attractionInfoMapper.getAttraction(id);
    }

    @Override
    public List<Sido> getSido() throws SQLException {
        return attractionInfoMapper.getSido();
    }

    @Override
    public List<Gugun> getGugun(int sidoCode) throws SQLException {
        return attractionInfoMapper.getGugun(sidoCode);
    }

    @Override
    public List<AttractionInfoAndDescription> getTopAttraction() throws Exception {
        return attractionInfoMapper.getTopAttraction();
    }

    @Override
    public void likeInfo(int id) throws Exception {
        attractionInfoMapper.likeInfo(id);
    }

    @Override
    public void likeCancelInfo(int id) throws Exception {
        attractionInfoMapper.likeCancelInfo(id);
    }

    @Override
    public boolean checkInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception {

        String str = attractionInfoMapper.checkInfo(attractionInfoCheckDto);
        //true => 이미 추천 누름 / false => 추천 아직 안 누름
        return str == null ? false : true;
    }

    @Override
    public void insertCheckInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception {
        attractionInfoMapper.insertCheckInfo(attractionInfoCheckDto);
    }

    @Override
    public void deleteCheckInfo(AttractionInfoCheckDto attractionInfoCheckDto) throws Exception {
        attractionInfoMapper.deleteCheckInfo(attractionInfoCheckDto);
    }
}