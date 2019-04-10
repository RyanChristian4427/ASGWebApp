package com.assessment.asg;

import com.assessment.asg.config.StorageProperties;
import com.assessment.asg.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AsgApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AsgApplication.class, args);
    }

    @Bean
    @Profile("ci, development, devops, prod")
    public FlywayMigrationStrategy cleanMigrateStrategy() {
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }

    @Bean
    @Profile("development, devops, prod")
    CommandLineRunner init(final StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
