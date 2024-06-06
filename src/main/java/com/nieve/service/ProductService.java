package com.nieve.service;

import com.nieve.entity.*;
import com.nieve.model.*;
import com.nieve.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    public Page<Product> getPagedProductListInCategory(Integer categoryNo, int page, String criteria, String dir){
        Pageable p = PageRequest.of(page, 6, "asc".equals(dir) ? Sort.Direction.ASC : Sort.Direction.DESC, criteria);
        CategoryEntity ce = categoryRepository.findById(categoryNo).orElseThrow();
        Page<ProductEntity> pes = productRepository.findAllByCategory(ce, p);
        return pes.map(ProductEntity::toModel);
    }

    public Page<Product> getPagedProductList(String keyword, int page, String criteria, String dir){
        Pageable p = PageRequest.of(page, 6, "asc".equals(dir) ? Sort.Direction.ASC : Sort.Direction.DESC, criteria);
        Page<ProductEntity> pes = productRepository.findAllKeyword(keyword, p);
        return pes.map(ProductEntity::toModel);
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

    public List<Category> getCategoryListWithProductCount() {
        List<CategoryCountEntity> categoryCounts = categoryRepository.getCategoryCount();

        List<Category> categories = new ArrayList<>();
        for (CategoryCountEntity ce : categoryCounts) {
            Category c = Category.builder()
                    .categoryNo(ce.getCategoryNo())
                    .categoryName(ce.getCategoryName())
                    .categoryCount(ce.getCategoryCount())
                    .build();
            categories.add(c);
        }
        return categories;
    }

    public Product getProduct(Integer productNo) {
        ProductEntity productEntity = productRepository.findById(productNo).orElseThrow();
        return productEntity.toModel();
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
