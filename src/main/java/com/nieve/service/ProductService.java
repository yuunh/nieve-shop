package com.nieve.service;

import com.nieve.entity.*;
import com.nieve.model.Cart;
import com.nieve.model.File;
import com.nieve.model.Product;
import com.nieve.model.Review;
import com.nieve.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private CartRepository cartRepository;

    @Autowired private CategoryRepository categoryRepository;

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

    public void addCart(Cart cart) {

        ProductEntity pe = productRepository.findById(cart.getProductNo()).orElseThrow();
        MemberEntity me = memberRepository.findById(cart.getMemNo()).orElseThrow();
        CartEntity ce = CartEntity.builder()
                .cartStock(cart.getCartStock())
                .product(pe)
                .member(me)
                .build();
        cartRepository.save(ce);
    }

    public List<Product> getProductListByCategoryNo(int categoryNo, int limit) {
        CategoryEntity category = categoryRepository.findById(categoryNo).orElse(CategoryEntity.builder().categoryNo(-1).build());
        Example<ProductEntity> ex = Example.of(ProductEntity.builder().category(category).build());
        List<ProductEntity> items = productRepository.findBy(ex, q -> q.stream().limit(limit)).toList();
        List<Product> results = new ArrayList<>();
        for(ProductEntity pe : items){
            results.add(pe.toModel());
        }
        return results;
    }
}
