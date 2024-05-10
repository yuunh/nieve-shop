package com.nieve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
                ).formLogin(withDefaults());
//                .formLogin(form -> form.successForwardUrl("/")
//                        .loginProcessingUrl("/login")
//                        .loginPage("/login"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
