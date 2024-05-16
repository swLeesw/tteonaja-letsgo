package com.ssafy.tteonajaletsgo.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberSaveDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String userPassword;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String userNickname;
}
