package com.mrtimi.mower.batch.config;

import com.mrtimi.mower.batch.model.Lawn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LawnConfig {
    @Bean
    @Scope("singleton")
    public Lawn lawn() {
        return new Lawn(0, 0);
    }
}