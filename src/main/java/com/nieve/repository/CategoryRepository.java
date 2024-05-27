package com.nieve.repository;


import com.nieve.entity.CategoryCountEntity;
import com.nieve.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query(value = """
        SELECT  A.category_no as categoryNo, 
            A.category_name as categoryName, 
            IFNULL(PRD_CNT.cnt, 0) as categoryCount 
        FROM category A 
            LEFT JOIN (
                SELECT category_no, COUNT(*) cnt FROM product GROUP BY category_no
            ) PRD_CNT ON A.category_no = PRD_CNT.category_no
        ORDER BY A.category_no
    """, nativeQuery = true)
    List<CategoryCountEntity> getCategoryCount();

}
