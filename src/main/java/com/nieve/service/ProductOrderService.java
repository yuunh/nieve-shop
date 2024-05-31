package com.nieve.service;

import com.nieve.entity.ProductOrderEntity;
import com.nieve.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderService {
    @Autowired private ProductOrderRepository productOrderRepository;

    public int addOrder(Integer subtotal, String address) {

        ProductOrderEntity pe = ProductOrderEntity.builder()
                .address(address)
                .build();
        productOrderRepository.save(pe);

        return 0;
    }
}
