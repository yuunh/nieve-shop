package com.nieve.ctrl;

import com.nieve.config.CustomUser;
import com.nieve.model.Cart;
import com.nieve.model.Member;
import com.nieve.model.Product;
import com.nieve.service.CartService;
import com.nieve.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nieve.service.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class HomeController {

    @Autowired
    MemberService memberService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String root(Model m) {
        return index(m);
    }

    @GetMapping("/index.html")
    public String index(Model m) {

        m.addAttribute("user_name", "이윤화");

        List<Product> productList = productService.getProductList();
        List<Product> categories = new ArrayList<>();
        pickRandom(productService.getProductListByCategoryNo(1, 100)).ifPresent(categories::add);
        pickRandom(productService.getProductListByCategoryNo(2, 100)).ifPresent(categories::add);
        pickRandom(productService.getProductListByCategoryNo(3, 100)).ifPresent(categories::add);
        pickRandom(productService.getProductListByCategoryNo(4, 100)).ifPresent(categories::add);
        m.addAttribute("productList", productList);
        m.addAttribute("categories", categories);

        return "index";
    }

    private Optional<Product> pickRandom(List<Product> cate) {
        if (cate != null && !cate.isEmpty()) {

            int size = cate.size();

            Random r = new Random();

            int pick = r.nextInt(size);

            return Optional.of(cate.get(pick));

        } else {
            return Optional.empty();
        }
    }

    @GetMapping("/enrollForm.html")
    public String enrollForm() {

        return "enrollForm";
    }

    @GetMapping("/memberEnrollForm.html")
    public String memberEnrollForm() {

        return "memberEnrollForm";
    }

    @GetMapping("/tracking.html")
    public String tracking() {

        return "tracking";
    }

    @GetMapping("/payment.html")
    public String payment(Model m) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("auth " + authentication);
            CustomUser user = (CustomUser) authentication.getPrincipal();

            Member member = memberService.getMember(user.getMemNo());
            m.addAttribute("member", member);

            List<Cart> cartList = cartService.getCartOfMember(user.getMemNo());
            m.addAttribute("cartList", cartList);

        }

        return "payment";
    }

    @PostMapping("memberInsert")
    @ResponseBody
    public String memberInsert(@ModelAttribute Member member) {

        memberService.memberInsert(member);

        return "index.html";
    }

    @GetMapping("/myPage.html")
    public String myPage(Model m) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("auth " + authentication);
            CustomUser user = (CustomUser) authentication.getPrincipal();

            Member member = memberService.getMember(user.getMemNo());
            m.addAttribute("member", member);
        }
//        if (memNo != null) {
//            Member member = memberService.getMember(memNo);
//            m.addAttribute("member", member);
//        }
        return "myPage";
    }

    @PostMapping("memberUpdate")
    public String memberUpdate(@ModelAttribute Member member) {

        memberService.updateMember(member);

        return "redirect:/myPage.html";
    }
}
