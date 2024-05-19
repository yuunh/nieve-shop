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

    private int productNo;
    private String productName;
    private String productDesc;
    private double productPrice;
    private int productStock;
    private String productState;
    private int fileNo;
    private String fileName;
    private int categoryNo;
    private String categoryName;
}
