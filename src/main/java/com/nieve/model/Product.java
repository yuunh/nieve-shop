package com.nieve.model;

public class Product {

    private String productName;
    private String productImg;
    private double productPrice;

    public Product(String productName, String productImg, double productPrice) {
        this.productName = productName;
        this.productImg = productImg;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }




}
