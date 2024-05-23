package com.nieve.repository;

import com.nieve.entity.CategoryCountEntity;
import com.nieve.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

//    @Query("SELECT  A.*, IFNULL(PRD_CNT.cnt, 0) CNT FROM category A \n" +
//            "\tLEFT JOIN (\n" +
//            "\t\tSELECT category_no, COUNT(*) cnt FROM product GROUP BY category_no\n" +
//            "\t) PRD_CNT ON A.category_no = PRD_CNT.category_no\n" +
//            "ORDER BY A.category_no\n")
//    public List<CategoryCountEntity> getCategory();


}
