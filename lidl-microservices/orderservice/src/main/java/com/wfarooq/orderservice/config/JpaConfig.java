package com.wfarooq.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    private static final List<String> DUMMY_USERS = Arrays.asList(
            "john.doe",
            "jane.smith",
            "warehouse.admin"
    );

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            // Randomly select a dummy user
            int randomIndex = new Random().nextInt(DUMMY_USERS.size());
            return Optional.of(DUMMY_USERS.get(randomIndex));
        };
    }
}