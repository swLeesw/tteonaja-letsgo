package com.ssafy.tteonajaletsgo.dto.member;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MemberSaveDto {

    @NotBlank
    @Size(min = 5, max = 20, message="{Size.UserId.message}")
    private String userId;

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 8, max = 20, message="{Size.UserPassword.message}")
    private String userPassword;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String userNickname;

}
