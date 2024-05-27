package com.nieve.ctrl;

import com.nieve.model.Category;
import com.nieve.model.Product;
import com.nieve.model.Review;
import com.nieve.service.ProductService;
import com.nieve.service.ReviewService;
import com.nieve.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/product_paging.html")
    @ResponseBody
    public Page<Product> category(@RequestParam(value = "categoryNo", required = false) Integer categoryNo,
                                  @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                  @RequestParam(value = "criteria", required = false, defaultValue = "productNo") String order,
                                  @RequestParam(value="dir", required = false, defaultValue = "desc") String dir) {

        if(categoryNo == null){
            return productService.getPagedProductList(page, order, dir);
        }else {
            return productService.getPagedProductListInCategory(categoryNo, page, order, dir);
        }
    }


    @GetMapping("/category.html")
    public String category(@RequestParam(value = "categoryNo", required = false) Integer categoryNo,
                           @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(value = "criteria", required = false, defaultValue = "productNo") String order,
                           @RequestParam(value="dir", required = false, defaultValue = "desc") String dir,
                           Model m) {

        Page<Product> productList;
        if(categoryNo == null){
            productList = productService.getPagedProductList(page, order, dir);
        }else {
            productList =  productService.getPagedProductListInCategory(categoryNo, page, order, dir);
        }
        m.addAttribute("productList", productList);

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
