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
            FileEntity fe = pe.getFile();
            Product p = Product.builder()
                    .productName(pe.getProductName())
                    .productPrice(pe.getProductPrice())
                    .fileName(fe.getChangeName())
                    .build();
            products.add(p);
        }

        return products;
    }

    public List<Review> getReviewList() {

        List<ReviewEntity> reviewList = reviewRepository.findAll();
        List<Review> reviews = new ArrayList<>();
        for (ReviewEntity re : reviewList) {
            FileEntity fe = re.getFile();
            Review r = Review.builder()
                    .reviewTitle(re.getReviewTitle())
                    .reviewContent(re.getReviewContent())
                    .fileName(fe.getChangeName())
                    .build();
            reviews.add(r);
        }

        return reviews;
    }

    public List<Product> getCartList() {

        List<ProductEntity> cartList = productRepository.findAll();

        List<Product> carts = new ArrayList<>();
        for (ProductEntity pe : cartList) {
            FileEntity fe = pe.getFile();
            Product p = Product.builder()
                    .productName(pe.getProductName())
                    .productPrice(pe.getProductPrice())
                    .fileName(fe.getChangeName())
                    .build();
            carts.add(p);
        }

        return carts;
    }
}
