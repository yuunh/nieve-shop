package com.nieve.ctrl;

import com.nieve.config.CustomUser;
import com.nieve.model.Cart;
import com.nieve.model.Member;
import com.nieve.service.CartService;
import com.nieve.service.MemberService;
import com.nieve.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/cart.html")
    public String productCart(@AuthenticationPrincipal CustomUser user, Model m) {
        System.out.println("Customer Info : ");
        System.out.println(user);

        List<Cart> cartList = cartService.getCartOfMember(user.getMemNo());

        m.addAttribute("cartList", cartList);

        return "cart";
    }

    @PostMapping("/addCart")
    @ResponseBody
    public String addCart(@RequestBody Cart cart) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println("auth " + authentication);
            CustomUser user = (CustomUser) authentication.getPrincipal();
            Member member = memberService.getMember(user.getMemNo());
            cart.setMemNo(member.getMemNo());
        }

        cartService.addCart(cart);

        return "{ 'result' : true }";
    }

    @DeleteMapping("/cart/delete-item/{cartNo}")
    public ResponseEntity deleteById(@PathVariable ("cartNo") Integer cartNo) {
        cartService.deleteById(cartNo);

        return new ResponseEntity(HttpStatus.OK);
    }
}
