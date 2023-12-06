package com.example.carshop.App;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

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
}
