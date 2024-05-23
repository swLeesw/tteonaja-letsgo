package com.ssafy.tteonajaletsgo.controller;

import com.ssafy.tteonajaletsgo.domain.Member;
import com.ssafy.tteonajaletsgo.dto.member.*;
import com.ssafy.tteonajaletsgo.exception.ExceptionResponse;
import com.ssafy.tteonajaletsgo.service.MailService;
import com.ssafy.tteonajaletsgo.service.MemberService;
import com.ssafy.tteonajaletsgo.util.JWTUtil;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    private final MailService mailService;
    private int number;

    @Operation(summary = "회원가입", description = "정보를 입력하여 회원가입을 한다.")
    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated @RequestBody MemberSaveDto memberSaveDto) {

        try {
            memberService.join(memberSaveDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }


    @Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @Validated @RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberLoginDto memberDto) {
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
    	System.out.println("재발급 들어왔다.");
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

    @Operation(summary = "아이디 중복 체크", description = "회원가입 전 아이디 중복 체크.")
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
            return ExceptionResponse.response(e);
        }
    }

    @Operation(summary = "비밀번호 체크", description = "회원정보 수정 전 비밀번호 확인")
    @PostMapping("/passcheck")
    public ResponseEntity<?> checkPass(@RequestBody(required = true) PassCheckDto passCheckDto) {

        try {
            IdCheckDto passCheck = new IdCheckDto();
            if (memberService.checkPass(passCheckDto)) {
                passCheck.setCheck(true);
            } else {
                passCheck.setCheck(false);
            }
            return new ResponseEntity<IdCheckDto>(passCheck, HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    @Operation(summary = "회원정보 삭제", description = "회원정보를 삭제한다")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId", required = true) String userId) {

        try {
            if (memberService.deleteUser(userId)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    @Operation(summary = "회원정보 수정", description = "수정 가능한 정보(password, email, phone, userNickname)")
    @PutMapping
    public ResponseEntity<?> modifyUser(@Validated @RequestBody MemberUpdateDto memberUpdateDto) {

        try {
            memberService.modify(memberUpdateDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ExceptionResponse.response(e);
        }

    }

    // 인증 이메일 전송
    @PostMapping("/mailSend")
    public ResponseEntity<?> mailSend(@RequestParam("userId") String userId) {
        Map<String, Object> map = new HashMap<>();
        try {

            String mail = memberService.getEmail(userId);
            System.out.println(mail);
            if (mail == null) {
                map.put("check", false);
            } else {
                number = mailService.sendMail(mail);
                String num = String.valueOf(number);
                map.put("check", true);
                map.put("email", mail);
                map.put("number", num);
            }
        } catch (Exception e) {
            map.put("check", false);
            map.put("error", e.getMessage());
        }

        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    // 인증번호 일치여부 확인
    @GetMapping("/mailCheck")
    public ResponseEntity<?> mailCheck(@RequestParam("userId") String userId, @RequestParam("userNumber") String userNumber) {
        System.out.println(userNumber);
        System.out.println("number는" + number);
        log.info("userNumber : {}", userNumber);
        boolean isMatch = userNumber.equals(String.valueOf(number));
        MailCheckDto mailCheckDto = new MailCheckDto();
        try {
            if (isMatch) {
                mailCheckDto.setCheck(true);
                mailCheckDto.setPassword(memberService.getPassword(userId));
            } else {
                mailCheckDto.setCheck(false);
            }
            return new ResponseEntity<MailCheckDto>(mailCheckDto, HttpStatus.OK);
        } catch (Exception e) {
            return ExceptionResponse.response(e);
        }
    }

    public String getPassword(String userId) throws Exception {
        return memberService.getPassword(userId);
    }

}
