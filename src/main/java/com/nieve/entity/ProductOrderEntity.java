package com.nieve.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity(name = "productOrder")
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ProductOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNo;
    @ColumnDefault("'등록'")
    private String orderState;
    private String address;
    private Integer postNo;
    private String phone;
    private Integer totalPrice;
    private String message;


    @ManyToOne
    @JoinColumn(name="memNo", unique = false)
    private MemberEntity member;

}
