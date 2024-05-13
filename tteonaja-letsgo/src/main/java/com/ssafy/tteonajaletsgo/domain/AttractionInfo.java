package com.ssafy.tteonajaletsgo.domain;

import lombok.Data;

@Data
public class AttractionInfo {
    int id;
    int typeId;
    String name;
    String zipcode;
    String tel;
    String firstImage;
    String latitude;
    String longitude;
    int sidoCode;
    int gugunCode;
    int like;
}
