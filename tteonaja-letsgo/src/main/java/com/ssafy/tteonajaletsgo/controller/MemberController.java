package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.Member;
import com.ssafy.tteonajaletsgo.dto.member.IdCheckDto;
import com.ssafy.tteonajaletsgo.dto.member.MemberLoginDto;
import com.ssafy.tteonajaletsgo.service.MemberService;
import com.ssafy.tteonajaletsgo.util.JWTUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("tteonaja/api/member")
public class MemberController {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberLoginDto memberDto) {
        log.debug("login user : {}", memberDto);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            Member loginUser = memberService.login(memberDto);
            if(loginUser != null) {
                String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
                String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
                log.debug("access token : {}", accessToken);
                log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token 을 DB에 저장.
                memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON 으로 token 전달.
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);

                status = HttpStatus.CREATED;
            } else {
                resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }

        } catch (Exception e) {
            log.debug("로그인 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("userId") @Parameter(description = "인증할 회원의 아이디.", required = true) String userId,
            HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                Member memberDto = memberService.userInfo(userId);
                log.info("memberDto = {}", memberDto);
                resultMap.put("userInfo", memberDto);
                status = HttpStatus.OK;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
    @GetMapping("/logout/{userId}")
    @Hidden
    public ResponseEntity<?> removeToken(@PathVariable ("userId") @Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            memberService.deleRefreshToken(userId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Member memberDto, HttpServletRequest request)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refreshToken");
        log.debug("token : {}, memberDto : {}", token, memberDto);
        if (jwtUtil.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
                String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 access token 재발급!!!");
                resultMap.put("access-token", accessToken);
                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("refresh token 도 사용 불가!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/idcheck/{userId}")
    public ResponseEntity<?> checkId(@PathVariable(value = "userId", required = true) String userId) {
        try {
            IdCheckDto idCheckDto = new IdCheckDto();
            if (memberService.checkId(userId)) {
                idCheckDto.setCheck(true);
            } else {
                idCheckDto.setCheck(false);
            }
            return new ResponseEntity<IdCheckDto>(idCheckDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("checkError", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
