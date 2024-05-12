package com.nieve.ctrl;

import com.nieve.model.Member;
import com.nieve.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nieve.service.MemberService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired MemberService memberService;

    @GetMapping("/")
    public String root(Model m) {
        m.addAttribute("user_name", "이윤화");
        memberService.saveMember("hello@test.com");
        return "index";
    }

    @GetMapping("/index.html")
    public String index(Model m) {
        m.addAttribute("user_name", "이윤화");
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

}
