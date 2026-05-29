package com.example.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// Spring Configuration class that sets up the 'DataSource' and 'JdbcTemplate' beans
@Configuration
@ComponentScan("com.example.dao")
public class JdbcConfig {

    // Database Connectivity
    @Bean("dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/wipro_training");
        dataSource.setUsername("root");
        dataSource.setPassword("tiger");   // ← change to YOUR MySQL password
        return dataSource;
    }

    // Database operations bean
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());  // setter-based DI
        return jdbcTemplate;
    }
}