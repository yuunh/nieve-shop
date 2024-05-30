package com.nieve.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductOrder {

    private int orderNo;
    private String productName;
    private int productPrice;
    private String memEmail;
    private String orderState;

}
