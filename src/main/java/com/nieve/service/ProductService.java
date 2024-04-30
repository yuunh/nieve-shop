package com.nieve.service;

import com.nieve.entity.ProductEntity;
import com.nieve.entity.ReviewEntity;
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
            Product p = Product.builder()
                    .productName(pe.getProductName())
                    .productPrice(pe.getProductPrice())
                    .productImg(pe.getProductImg())
                    .build();
            products.add(p);
        }

        return products;
    }

    public List<Review> getReviewList() {

        List<ReviewEntity> reviewList = reviewRepository.findAll();

        List<Review> reviews = new ArrayList<>();
        for (ReviewEntity re : reviewList) {
            Review r = Review.builder()
                    .reviewTitle(re.getReviewTitle())
                    .reviewContent(re.getReviewContent())
                    .build();
            reviews.add(r);
        }

        return reviews;
    }

    public List<Product> getCartList() {

        List<ProductEntity> cartList = productRepository.findAll();

        List<Product> carts = new ArrayList<>();
        for (ProductEntity pe : cartList) {
            Product p = Product.builder()
                    .productName(pe.getProductName())
                    .productImg(pe.getProductImg())
                    .productPrice(pe.getProductPrice())
                    .build();
            carts.add(p);
        }

        return carts;
    }
}
