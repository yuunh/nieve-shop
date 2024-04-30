package com.nieve.repository;

import com.nieve.entity.MemberEntity;
import com.nieve.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
}
