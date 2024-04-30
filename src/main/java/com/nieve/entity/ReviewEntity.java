package com.nieve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Entity(name = "review")
@Data
@Getter
public class ReviewEntity {

    @Id
    private int reviewNo;
    private String reviewTitle;
    private String reviewContent;
}
