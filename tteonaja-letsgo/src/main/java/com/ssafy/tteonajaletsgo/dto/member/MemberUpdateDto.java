package com.ssafy.tteonajaletsgo.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberUpdateDto {

    @NotBlank
    private String userId;

    private String userPassword;

    @Email
    private String email;

    private String phone;

    private String userNickname;

}
