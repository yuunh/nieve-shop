package com.nieve.repository;

import com.nieve.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query("select r from review as r where r.product.productNo = :productNo")
    List<ReviewEntity> findByProductNo(Integer productNo);
}
