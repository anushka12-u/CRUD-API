package com.example.RESTAPI.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapprConfig {
    @Bean
    public ModelMapper modelmap()
    {
        return new ModelMapper();
    }
}
