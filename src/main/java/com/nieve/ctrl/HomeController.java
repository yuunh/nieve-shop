package com.nieve.ctrl;

import com.nieve.config.CustomUser;
import com.nieve.model.Member;
import com.nieve.model.Product;
import com.nieve.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nieve.service.MemberService;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    MemberService memberService;

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String root(Model m) {

        m.addAttribute("user_name", "이윤화");
        memberService.saveMember("hello@test.com");

        List<Product> productList = productService.getProductList();
        m.addAttribute("productList", productList);

        return "index";
    }

    @GetMapping("/index.html")
    public String index(Model m) {

        m.addAttribute("user_name", "이윤화");

        List<Product> productList = productService.getProductList();
        m.addAttribute("productList", productList);

        return "index";
    }

    @GetMapping("/login.html")
    public String login() {

        return "login";
    }

    @GetMapping("/memberEnrollForm.html")
    public String enrollForm() {

        return "memberEnrollForm";
    }

    @GetMapping("/tracking.html")
    public String tracking() {

        return "tracking";
    }

    @GetMapping("/checkout.html")
    public String checkout() {

        return "checkout";
    }

    @PostMapping("memberInsert")
    @ResponseBody
    public String memberInsert(@ModelAttribute Member member) {

        memberService.memberInsert(member);

        return "ok";
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
