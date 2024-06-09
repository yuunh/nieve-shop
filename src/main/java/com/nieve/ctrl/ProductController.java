package com.nieve.ctrl;

import com.nieve.config.CustomUser;
import com.nieve.model.*;
import com.nieve.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping("/product_paging.html")
    @ResponseBody
    public Page<Product> category(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                  @RequestParam(value = "categoryNo", required = false) Integer categoryNo,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                  @RequestParam(value = "criteria", required = false, defaultValue = "productNo") String order,
                                  @RequestParam(value="dir", required = false, defaultValue = "desc") String dir) {

        if(categoryNo == null){
            return productService.getPagedProductList(keyword, page, order, dir);
        } else {
            return productService.getPagedProductListInCategory(categoryNo, page, order, dir);
        }
    }

    @GetMapping("/category.html")
    public String category(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                           @RequestParam(value = "categoryNo", required = false) Integer categoryNo,
                           @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(value = "criteria", required = false, defaultValue = "productNo") String order,
                           @RequestParam(value="dir", required = false, defaultValue = "desc") String dir,
                           Model m) {

        Page<Product> productList;
        if(categoryNo == null){
            productList = productService.getPagedProductList(keyword, page, order, dir);
        } else {
            productList = productService.getPagedProductListInCategory(categoryNo, page, order, dir);
        }
        m.addAttribute("productList", productList);
        m.addAttribute("productCount", productList.getTotalElements());

        List<Category> categoryList = productService.getCategoryListWithProductCount();
        m.addAttribute("categoryList", categoryList);

        m.addAttribute("categoryNo", categoryNo);
        m.addAttribute("criteria", order);
        m.addAttribute("dir", dir);
        return "category";
    }


    @GetMapping("/single-product.html")
    public String productDetail(@RequestParam("productNo") Integer productNo, Model m) {
        // get product from productservice

        Product product = productService.getProduct(productNo);
        m.addAttribute("product", product);

        List<Review> reviewList = reviewService.getReviewList(productNo);
        m.addAttribute("reviewList", reviewList);

        return "single-product";
    }

    @PostMapping("/orderAdd")
    @ResponseBody
    public String orderAdd(@RequestBody ProductOrder productOrder) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("auth " + authentication);
            CustomUser user = (CustomUser) authentication.getPrincipal();

            Integer memNo = user.getMemNo();

            Member member = memberService.getMember(memNo);
            //Member member = memberService.getMember(user.getMemNo());

            List<Cart> cartList = cartService.getCartOfMember(memNo);
            //List<Cart> cartList = cartService.getCartOfMember(user.getMemNo());

            Integer subTotal = 0;
            for(Cart c : cartList){
                Integer price = c.getCartStock() * c.getProduct().getProductPrice();
                subTotal += price;
            }

            productOrderService.addOrder(subTotal, productOrder.getAddress(), productOrder.getMessage(), productOrder.getPhone(), productOrder.getPostNo());

        }
        return "ok";
    }

    @GetMapping("/confirmation.html")
    public String confirmation(Model m) {

        List<ProductOrder> orderList = productOrderService.getOrderList();

        m.addAttribute("orderList", orderList);

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
