package com.nieve.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cart {

    private Integer cartNo;
    private Integer memNo;
    private Integer productNo;
    private Integer cartStock;
    private Product product;

}
