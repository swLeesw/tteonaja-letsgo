package com.ssafy.tteonajaletsgo.dto;

import lombok.Data;

@Data
public class AttractionInfoAndDescription {
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
    private String homepage;
    private String overview;
}
