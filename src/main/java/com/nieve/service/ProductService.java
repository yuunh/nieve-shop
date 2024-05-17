package com.nieve.service;

import com.nieve.entity.FileEntity;
import com.nieve.entity.ProductEntity;
import com.nieve.entity.ReviewEntity;
import com.nieve.model.File;
import com.nieve.model.Product;
import com.nieve.model.Review;
import com.nieve.repository.ProductRepository;
import com.nieve.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private ReviewRepository reviewRepository;

    public List<Product> getProductList() {
        List<ProductEntity> productList = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for (ProductEntity pe : productList) {
            products.add(pe.toModel());
        }

        return products;
    }

    public List<Product> getCartList() {
        List<ProductEntity> cartList = productRepository.findAll();

        List<Product> carts = new ArrayList<>();
        for (ProductEntity pe : cartList) {
            carts.add(pe.toModel());
        }

        return carts;
    }

    public Product getProduct(Integer productNo) {
        ProductEntity productEntity = productRepository.findById(productNo).orElseThrow();
        return productEntity.toModel();
    }
}
