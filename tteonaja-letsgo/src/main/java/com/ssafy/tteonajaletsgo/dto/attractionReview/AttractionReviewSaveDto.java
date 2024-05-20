package com.ssafy.tteonajaletsgo.dto.attractionReview;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class AttractionReviewSaveDto {
    @NotBlank
    private String userId;

    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String content;

    @NotBlank
    private String attractionId;
}
