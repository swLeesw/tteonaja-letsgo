package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class Member {
    private String userId;
    private String userName;
    private String userPassword;
    private String email;
    private String phone;
    private String userNickname;
    private String joinData;
    private String lastModifiedDate;
}
