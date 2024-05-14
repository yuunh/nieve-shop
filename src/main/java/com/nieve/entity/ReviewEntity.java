package com.nieve.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "review")
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    private int reviewNo;
    private String reviewTitle;
    private String reviewContent;

    //@Column(name = "review_star", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int reviewStar;


    @OneToOne
    @JoinColumn(name="fileNo", unique = false)
    private FileEntity file;


}
