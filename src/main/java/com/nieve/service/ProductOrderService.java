package com.nieve.service;

import com.nieve.entity.ProductOrderEntity;
import com.nieve.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ProductOrderService {
    @Autowired private ProductOrderRepository productOrderRepository;

    public int addOrder(Integer subtotal, String address, String message, String phone, String postNo) {
        String ymd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHH"));
        String rndstr = UUID.randomUUID().toString().substring(0, 4);

        ProductOrderEntity pe = ProductOrderEntity.builder()
                .orderNo(ymd + rndstr)
                .totalPrice(subtotal)
                .address(address)
                .message(message)
                .phone(phone)
                .postNo(postNo)
                .build();
        productOrderRepository.save(pe);

        return 0;
    }


}
