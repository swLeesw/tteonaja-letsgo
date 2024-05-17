package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.domain.Member;
import com.ssafy.tteonajaletsgo.dto.member.MemberLoginDto;
import com.ssafy.tteonajaletsgo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;

    @Override
    public Member login(MemberLoginDto memberDto) throws Exception {
        return memberMapper.login(memberDto);
    }

    @Override
    public Member userInfo(String userId) throws Exception {
        return memberMapper.userInfo(userId);
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("token", refreshToken);
        memberMapper.saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userId) throws Exception {
        return memberMapper.getRefreshToken(userId);
    }

    @Override
    public void deleRefreshToken(String userId) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("token", null);
        memberMapper.deleteRefreshToken(map);
    }

    @Override
    public boolean checkId(String userId) throws Exception {
        String mId = memberMapper.checkId(userId);
        if (mId != null) {
            return true;
        }
        return false;
    }
}
