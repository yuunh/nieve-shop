package com.nieve.config;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@ToString
public class CustomUser extends User {
    private Integer memNo;
    public CustomUser(String username, String password, Integer memberNo, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.memNo = memberNo;
    }

    public Integer getMemNo(){
        return this.memNo;
    }
}
