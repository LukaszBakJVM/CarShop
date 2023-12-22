package com.example.carshop.App.Compononent;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.math.BigDecimal;

@org.springframework.context.annotation.Configuration


public class Configuration {

    @Bean
    BigDecimal bigDecimal(){
        return new BigDecimal("0.0");
    }
    @Bean
    ObjectMapper objectMapper (){
        return new ObjectMapper();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        PathRequest.H2ConsoleRequestMatcher h2Console = PathRequest.toH2Console();
       http.csrf(h2->h2.ignoringRequestMatchers(h2Console));

        http.formLogin(e->e.loginPage("/login").permitAll());
       // http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        http.authorizeHttpRequests(a -> a.requestMatchers("/", "/img/**", "/register.html",
                        "/register","/login.html","/index.html","/basket")
                .permitAll().requestMatchers(h2Console).permitAll().anyRequest().authenticated());
        http.csrf(i->i.ignoringRequestMatchers("/**")).formLogin(AbstractAuthenticationFilterConfigurer::permitAll);


        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
       .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}