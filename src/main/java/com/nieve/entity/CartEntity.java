package com.nieve.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity(name = "cart")
@Data
@Getter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartNo;
    private int cartStock;

    @ManyToOne
    @JoinColumn(name = "fileNo", unique = false)
    private FileEntity file;

    @ManyToOne
    @JoinColumn(name = "memNo", unique = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "productNo", unique = false)
    private ProductEntity product;

}
