package com.nieve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;

@Entity(name = "product")
@Data
@Getter
public class ProductEntity {

    @Id
    private int productNo;
    private String productName;
    private double productPrice;

    @OneToOne
    @JoinColumn(name="fileNo", unique = false)
    private FileEntity file;
}
