package com.htlabs.smartwatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.htlabs.smartwatch.repository")
@EntityScan("com.htlabs.smartwatch.entity")
@EnableTransactionManagement
@EnableScheduling
public class SmartWatchApplication {

    public static void main(String[] args) {
        System.out.println("Welcome");
        SpringApplication.run(SmartWatchApplication.class, args);
    }
}
