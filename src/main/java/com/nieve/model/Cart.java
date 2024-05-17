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

    private int cartNo;
    private int memNo;
    private int productNo;
    private String productName;
    private int productPrice;
    private int cartStock;
    private int fileNo;
    private String fileName;
    private int categoryNo;
    private String categoryName;
}
