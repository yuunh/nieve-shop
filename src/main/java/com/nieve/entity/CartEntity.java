package com.nieve.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "cart")
@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartNo;
    private Integer cartStock;

    @ManyToOne
    @JoinColumn(name = "memNo", unique = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "productNo", unique = false)
    private ProductEntity product;

}
