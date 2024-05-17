package com.nieve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Entity(name = "cart")
@Data
@Getter
public class CartEntity {

    @Id
    private int cartNo;
    private int cartStock;

    @OneToOne
    @JoinColumn(name = "fileNo", unique = false)
    private FileEntity file;

    @OneToOne
    @JoinColumn(name = "memNo", unique = false)
    private MemberEntity member;

    @OneToOne
    @JoinColumn(name = "productNo", unique = false)
    private ProductEntity product;

}
