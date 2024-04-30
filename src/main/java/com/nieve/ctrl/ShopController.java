package com.nieve.ctrl;

import com.nieve.model.Product;
import com.nieve.model.Review;
import com.nieve.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ShopController {

    @Autowired private ProductService productService;

    @GetMapping("/category.html")
    public String category(Model m) {

        List<Product> productList = productService.getProductList();

        m.addAttribute("productList", productList);

        return "category";
    }

    @GetMapping("/single-product.html")
    public String review(Model m) {

        List<Review> reviewList = productService.getReviewList();

        m.addAttribute("reviewList", reviewList);

        return "single-product";
    }

    @GetMapping("/cart.html")
    public String productCart(Model m) {

        List<Product> cartList = productService.getCartList();

        cartList.add(new Product("Colorful Stylish Shirt", "img/product/product_5.png", 150.00));
        cartList.add(new Product("Full Skirt Dress", "img/product/product_2.png", 160.00));
        cartList.add(new Product("Trendy Blouse", "img/product/product_4.png", 180.00));

        m.addAttribute("cartList", cartList);

        return "cart";
    }

    @GetMapping("/confirmation.html")
    public String confirmation(Model m) {

        List<Product> confirmationList = new ArrayList<>();

        confirmationList.add(new Product("Colorful Stylish Shirt", "img/product/product_5.png", 150.00));
        confirmationList.add(new Product("Beautiful Skirt", "img/product/product_3.png", 200.00));
        confirmationList.add(new Product("BodyCon Dress", "img/product/product_6.png", 130.00));

        m.addAttribute("confirmationList", confirmationList);

        return "confirmation";
    }
}
