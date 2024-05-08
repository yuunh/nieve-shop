package com.nieve.entity;

import jakarta.persistence.*;
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

    @OneToOne
    @JoinColumn(name="fileNo", unique = false)
    private FileEntity file;
}
