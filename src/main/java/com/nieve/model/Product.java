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
    private int fileNo1;
    private String fileName1;
    private int fileNo2;
    private String fileName2;
    private int fileNo3;
    private String fileName3;
    private int categoryNo;
    private String categoryName;
}
