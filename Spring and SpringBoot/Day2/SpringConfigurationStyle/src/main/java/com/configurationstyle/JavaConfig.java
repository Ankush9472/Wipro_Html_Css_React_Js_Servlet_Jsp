package com.configurationstyle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.configurationstyle")
public class JavaConfig {

    @Bean
    Allocator allocator() {
        return new Manager();
    }

    @Bean
    DelegateXmlBased delegate(Allocator allocator) {
        return new DelegateXmlBased(allocator);
    }
}