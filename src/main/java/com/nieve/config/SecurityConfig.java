package com.nieve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

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
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/cart").hasRole("USER")
                    .requestMatchers("/writeReview").hasRole("USER")
                    .requestMatchers("/**").hasRole("USER")
                )
                .formLogin(form -> form.defaultSuccessUrl("/"));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        List<GrantedAuthority> auths = new ArrayList<>();
//        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        List<GrantedAuthority> authsAdmin = new ArrayList<>();
//        authsAdmin.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        authsAdmin.add(new SimpleGrantedAuthority("ROLE_USER"));
//        User user = new CustomUser("1", "1234", 1, auths);
//        User admin = new CustomUser("2", "1234", 2, authsAdmin);
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder encrypt(){

        return new BCryptPasswordEncoder();
    }
}


