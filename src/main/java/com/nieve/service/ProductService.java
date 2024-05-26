package com.nieve.service;

import com.nieve.entity.*;
import com.nieve.model.*;
import com.nieve.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
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

    public List<Product> getProductListByFilterWithSort(Integer categoryNo, String sortField, String sortDirection) {
        String sortProperties = sortField != null ? sortField : "productNo";
        Sort sort = "asc".equals(sortDirection) ?
                Sort.by(sortProperties).ascending()
                : Sort.by(sortProperties).descending();

        if(categoryNo != null){
            CategoryEntity ce = categoryRepository.findById(categoryNo).orElseThrow();
            Example<ProductEntity> ex = Example.of(ProductEntity.builder().category(ce).build());
            return getProductList(ex, sort);
        }else
            return getProductList(Example.of(ProductEntity.builder().build()), sort);
    }

    public List<Product> getProductList(){
        return getProductList(Example.of(ProductEntity.builder().build()), Sort.by("productNo"));
    }

    public List<Product> getProductList(Example<ProductEntity> example, Sort sort) {
        List<ProductEntity> productList = productRepository.findBy(example, q -> q.sortBy(sort).all()).stream().toList();
        List<Product> products = new ArrayList<>();
        for (ProductEntity pe : productList) {
            products.add(pe.toModel());
        }

        return products;
    }

    public List<Category> getCategoryList() {

        List<CategoryEntity> catgoryList = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity ce : catgoryList) {
            Category c = Category.builder()
                    .categoryNo(ce.getCategoryNo())
                    .categoryName(ce.getCategoryName())
                    .build();
            categories.add(c);
        }
        return categories;
    }

    public List<Cart> getCartList() {
        List<CartEntity> cartList = cartRepository.findAll();

        List<Cart> carts = new ArrayList<>();
        for (CartEntity ce : cartList) {
            ProductEntity pe = ce.getProduct();
            Product p = pe.toModel();
            Cart c = Cart.builder()
                    .cartNo(ce.getCartNo())
                    .cartStock(ce.getCartStock())
                    .productNo(pe.getProductNo())
                    .product(p)
                    .build();
            carts.add(c);
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
