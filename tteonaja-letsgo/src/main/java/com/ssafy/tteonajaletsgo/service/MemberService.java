package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.Member;
import com.ssafy.tteonajaletsgo.dto.member.MemberLoginDto;
import com.ssafy.tteonajaletsgo.dto.member.MemberSaveDto;
import com.ssafy.tteonajaletsgo.dto.member.MemberUpdateDto;
import com.ssafy.tteonajaletsgo.dto.member.PassCheckDto;

public interface MemberService {
    Member login(MemberLoginDto memberDto) throws Exception;
    Member userInfo(String userId) throws Exception;
    void saveRefreshToken(String userId, String refreshToken) throws Exception;
    Object getRefreshToken(String userId) throws Exception;
    void deleRefreshToken(String userId) throws Exception;
    boolean checkId(String userId) throws Exception;
    boolean checkPass(PassCheckDto passCheckDto) throws Exception;
    void join(MemberSaveDto memberSaveDto) throws Exception;
    boolean deleteUser(String userId) throws Exception;
    void modify(MemberUpdateDto memberUpdateDto) throws Exception;
    String getPassword(String userId) throws Exception;
    String getEmail(String userId) throws Exception;
}
