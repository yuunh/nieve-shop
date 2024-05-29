package com.nieve.entity;

import com.nieve.model.Cart;
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
    private Integer cartNo;
    private Integer cartStock;

    @ManyToOne
    @JoinColumn(name = "memNo", unique = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "productNo", unique = false)
    private ProductEntity product;

    public Cart toModel(){
        return Cart.builder()
                .cartNo(getCartNo())
                .memNo(member.getMemNo())
                .productNo(product.getProductNo())
                .cartStock(getCartStock())
                .product(product.toModel())
                .build();
    }
}
