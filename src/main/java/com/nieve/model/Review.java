package com.nieve.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Review {

    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;
    private int fileNo;
    private String fileName;
}
