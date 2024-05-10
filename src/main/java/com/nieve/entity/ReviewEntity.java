package com.nieve.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity(name = "review")
@Data
@Getter
@Builder
public class ReviewEntity {

    @Id
    private int reviewNo;
    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;

    @OneToOne
    @JoinColumn(name="fileNo", unique = false)
    private FileEntity file;
}
