package com.ssafy.tteonajaletsgo.mapper;

import com.ssafy.tteonajaletsgo.domain.Member;
import com.ssafy.tteonajaletsgo.dto.member.MemberLoginDto;
import com.ssafy.tteonajaletsgo.dto.member.MemberSaveDto;
import com.ssafy.tteonajaletsgo.dto.member.MemberUpdateDto;
import com.ssafy.tteonajaletsgo.dto.member.PassCheckDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {
    Member login(MemberLoginDto memberDto) throws SQLException;
    Member userInfo(String userId) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    Object getRefreshToken(String userid) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
    String checkId(String userId) throws Exception;
    String checkPass(PassCheckDto passCheckDto) throws Exception;
    void join(MemberSaveDto memberSaveDto) throws Exception;
    int deleteUser(String userId) throws Exception;
    void modify(MemberUpdateDto memberUpdateDto) throws Exception;
    String getPassword(String userId) throws Exception;
    String getEmail(String userId) throws Exception;
}
