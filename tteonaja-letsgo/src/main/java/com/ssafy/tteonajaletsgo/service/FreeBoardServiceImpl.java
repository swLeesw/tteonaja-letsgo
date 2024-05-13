package com.ssafy.tteonajaletsgo.service;

import com.ssafy.tteonajaletsgo.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

    private final FreeBoardMapper freeBoardMapper;

}
