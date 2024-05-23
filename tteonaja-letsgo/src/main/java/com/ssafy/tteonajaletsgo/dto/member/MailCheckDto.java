package com.ssafy.tteonajaletsgo.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailCheckDto {
    @NotBlank
    Boolean check;
    @NotBlank
    String password;
}
