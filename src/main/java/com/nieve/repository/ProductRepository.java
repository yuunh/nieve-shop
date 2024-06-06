package com.nieve.repository;

import com.nieve.entity.CategoryEntity;
import com.nieve.entity.ProductEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Page<ProductEntity> findAllByCategory(CategoryEntity category, Pageable p);

    @Query("select m from product as m where m.productName like concat('%', :keyword, '%')")
    Page<ProductEntity> findAllKeyword(String keyword, Pageable p);

//    Page<ProductEntity> findByCategory(Example<CategoryEntity> category, Pageable p);
}
