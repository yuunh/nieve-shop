package com.nieve.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity(name = "product")
@Data
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productNo;
    private String productName;
    private int productPrice;
    private int productStock;
    private String productState;

    @ManyToOne
    @JoinColumn(name="fileNo", unique = false)
    private FileEntity file;

    @ManyToOne
    @JoinColumn(name="categoryNo", unique = false)
    private CategoryEntity category;
}
