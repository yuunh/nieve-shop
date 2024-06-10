package com.nieve.service;

import com.nieve.entity.CartEntity;
import com.nieve.entity.MemberEntity;
import com.nieve.entity.ProductEntity;
import com.nieve.entity.ProductOrderEntity;
import com.nieve.model.ProductOrder;
import com.nieve.repository.MemberRepository;
import com.nieve.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductOrderService {
    @Autowired private ProductOrderRepository productOrderRepository;
    @Autowired private MemberRepository memberRepository;

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

    public List<ProductOrder> getOrderList(Integer memNo) {

        MemberEntity member = memberRepository.findById(memNo).orElseThrow();
        Example<CartEntity> ex = Example.of(CartEntity.builder().member(member).build());

        List<ProductOrderEntity> orderList = productOrderRepository.findAll();

        List<ProductOrder> orders = new ArrayList<>();
        for (ProductOrderEntity oe : orderList) {
            MemberEntity me = oe.getMember();
            ProductEntity pe = oe.getProduct();
            ProductOrder o = ProductOrder.builder()
                    .orderNo(oe.getOrderNo())
                    .memEmail(me.getMemEmail())
                    .productName(pe.getProductName())
                    .productPrice(pe.getProductPrice())
                    .orderState(oe.getOrderState())
                    .build();
            orders.add(o);
        }
        return orders;
    }

    public List<ProductOrder> getOrder(Integer memNo) {

        MemberEntity member = memberRepository.findById(memNo).orElseThrow();
        Example<ProductOrderEntity> ex = Example.of(ProductOrderEntity.builder().member(member).build());
        List<ProductOrderEntity> orders = productOrderRepository.findBy(ex, FluentQuery.FetchableFluentQuery::all).stream().toList();
        List<ProductOrder> orderList = new ArrayList<>();
        for (ProductOrderEntity oe : orders) {
        }
        return orderList;
    }
}
