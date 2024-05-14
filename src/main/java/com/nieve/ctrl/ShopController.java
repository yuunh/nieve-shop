package com.nieve.ctrl;

import com.nieve.model.Member;
import com.nieve.model.Product;
import com.nieve.model.Review;
import com.nieve.service.MemberService;
import com.nieve.service.ProductService;
import com.nieve.service.ReviewService;
import com.nieve.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ShopController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/category.html")
    public String category(Model m) {

        List<Product> productList = productService.getProductList();

        m.addAttribute("productList", productList);

        return "category";
    }

    @GetMapping("/single-product.html")
    public String review(Model m) {


        List<Review> reviewList = reviewService.getReviewList();

        m.addAttribute("reviewList", reviewList);

        return "single-product";
    }

    @GetMapping("/cart.html")
    public String productCart(Model m) {

        List<Product> cartList = productService.getCartList();

        m.addAttribute("cartList", cartList);

        return "cart";
    }

    @GetMapping("/confirmation.html")
    public String confirmation(Model m) {

        List<Product> confirmationList = new ArrayList<>();

        m.addAttribute("confirmationList", confirmationList);

        return "confirmation";
    }


    @PostMapping("/writeReview")
    @ResponseBody
    public String writeReview(@ModelAttribute Review review, @RequestParam("reviewImg") MultipartFile imageFile) {

        int fileNo = storageService.store(imageFile);
        review.setFileNo(fileNo);
        reviewService.addReview(review);

        return "redirect:/single-product.html";
    }
    
}
