package com.nieve.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {

    private String productName;
    private String productImg;
    private double productPrice;

}
