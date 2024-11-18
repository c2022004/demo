package com.example.projectsale.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class PermissionCodeConfig {

    private final Environment environment;

    @Bean
    public String adminMaster() {
        return environment.getProperty("admin.master");
    }
}
