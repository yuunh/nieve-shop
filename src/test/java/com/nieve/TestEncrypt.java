package com.nieve;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncrypt {
    @Test
    public void encodeTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("admin01");
        System.out.println("["+result+"]");

        System.out.println("is match : " + encoder.matches("test", result));
    }
}
