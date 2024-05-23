package com.ssafy.tteonajaletsgo.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailDto {
    @NotBlank
    private String Email;
}
