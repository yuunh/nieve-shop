package com.nieve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req) ->
            req.requestMatchers("/css/**").permitAll()
                    .requestMatchers("/fonts/**").permitAll()
                    .requestMatchers("/img/**").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/webfonts/**").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/cart").hasRole("USER")
                    .requestMatchers("/cart.html").hasRole("USER")
                    .requestMatchers("/writeReview").hasRole("USER")
                    .requestMatchers("/myPage.html").hasRole("USER")
                )
                .formLogin(form -> form.defaultSuccessUrl("/")
                        .loginPage("/login.html").permitAll()
                        .usernameParameter("memEmail")
                        .passwordParameter("memPwd"));

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder encrypt(){

        return new BCryptPasswordEncoder();
    }
}


