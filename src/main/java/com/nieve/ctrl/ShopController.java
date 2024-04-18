package com.nieve.ctrl;

import com.nieve.model.Product;
import com.nieve.model.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ShopController {

    @GetMapping("/category.html")
    public String category(Model m) {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Colorful Stylish Shirt", "img/product/product_5.png", 150.00));
        productList.add(new Product("Quartz Belt Watch", "img/product/product_1.png", 100.00));
        productList.add(new Product("Full Skirt Dress", "img/product/product_2.png", 160.00));
        productList.add(new Product("Beautiful Skirt", "img/product/product_3.png", 200.00));
        productList.add(new Product("Trendy Blouse", "img/product/product_4.png", 180.00));
        productList.add(new Product("BodyCon Dress", "img/product/product_6.png", 130.00));

        m.addAttribute("productList", productList);

        return "category";
    }

    @GetMapping("/single-product.html")
    public String review(Model m) {

        ArrayList<Review> reviewList = new ArrayList<>();

        reviewList.add(new Review("생각했던 원단재질이 아니라 조금실망스러워요", "55사이즈인 분들에게는 조금넉넉한핏입니다"));
        reviewList.add(new Review("베스트 코디아이템이 될것같습니다", "평소55반에서66사이즈입습니다\n" + "주줌옷은 좀많이큰편이여서 많이 망설이다가 픽!!!\n" + "입어보니 넘만족합니다 적당한여유핏과 가벼운착용감 스판소재"));
        reviewList.add(new Review("작년에 브라운 구입 후 올해는 네이비 구입했어요..", "네이비도 넘 하늘하늘 이뻐요.. 저는 아이보리 조끼랑 같이 입고 출근했어요..@^^@"));
        reviewList.add(new Review("잘입겠슴니다.", "제가 좋아하는 스타일이라 맘설임없이 주문했는데.\n" + "원단 고급지고 스타일도 예쁘네요.\n" + "약간 오버핏이네요."));
        reviewList.add(new Review("마음에 듭니다", "옷감이 부드럽고 색상도 환하니 얼굴이 더 밝아 보여서 마음에 듭니다."));

        m.addAttribute("reviewList", reviewList);

        return "single-product.html";
    }

    @GetMapping("/cart.html")
    public String productCart(Model m) {

        List<Product> cartList = new ArrayList<>();

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
