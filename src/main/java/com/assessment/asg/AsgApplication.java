package com.assessment.asg;

import com.assessment.asg.config.StorageProperties;
import com.assessment.asg.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static void main(final String[] args) {
        SpringApplication.run(AsgApplication.class, args);
    }

    @Bean
    @Profile("ci, development, devops, prod")
    public FlywayMigrationStrategy cleanMigrateStrategy() {
        return flyway -> {
            LOGGER.info("Cleaning Database");
            flyway.clean();
            LOGGER.info("Migrating Database");
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
