package com.nieve.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Integer cartNo;
    private Integer memNo;
    private Integer productNo;
    private Integer cartStock;
    private Product product;

}
