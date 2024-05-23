package com.ssafy.tteonajaletsgo.dto.travelCourse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TravelCourseCheckDto {

    @NotBlank
    private int articleNo;
    @NotBlank
    private String userId;

}
