package com.nieve.service;

import com.nieve.entity.CartEntity;
import com.nieve.entity.MemberEntity;
import com.nieve.entity.ProductEntity;
import com.nieve.model.Cart;
import com.nieve.model.Product;
import com.nieve.repository.CartRepository;
import com.nieve.repository.MemberRepository;
import com.nieve.repository.ProductRepository;
import com.nieve.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired private ProductRepository productRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartOfMember(Integer memberNo) {

        MemberEntity member = memberRepository.findById(memberNo).orElseThrow();
        Example<CartEntity> ex = Example.of(CartEntity.builder().member(member).build());
        List<CartEntity> carts = cartRepository.findBy(ex, FluentQuery.FetchableFluentQuery::all).stream().toList();
        List<Cart> cartList = new ArrayList<>();
        for(CartEntity ce : carts){
            cartList.add(ce.toModel());
            System.out.println(ce.toModel());
        }

        return cartList;
    }

    public void addCart(Cart cart) {

        ProductEntity pe = productRepository.findById(cart.getProductNo()).orElseThrow();
        MemberEntity me = memberRepository.findById(cart.getMemNo()).orElseThrow();
        CartEntity ce = CartEntity.builder()
                .cartStock(cart.getCartStock())
                .product(pe)
                .member(me)
                .build();
        cartRepository.save(ce);
    }


    public void deleteById(Integer cartNo) {
        cartRepository.deleteById(cartNo);
    }
}
