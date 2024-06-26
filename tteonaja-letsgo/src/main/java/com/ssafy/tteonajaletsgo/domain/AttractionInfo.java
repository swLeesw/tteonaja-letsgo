package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class AttractionInfo {
    private int id;
    private int typeId;
    private String name;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String latitude;
    private String longitude;
    private int sidoCode;
    private int gugunCode;
    private int like;
    private String addr1;
}
