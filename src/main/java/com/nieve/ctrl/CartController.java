package com.nieve.ctrl;

import com.nieve.model.Product;
import com.nieve.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @GetMapping("/cart.html")
    public String productCart(Model m) {

        List<Product> cartList = productService.getCartList();

        m.addAttribute("cartList", cartList);

        return "cart";
    }
}
