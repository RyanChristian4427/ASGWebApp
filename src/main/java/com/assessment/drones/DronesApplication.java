package com.assessment.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DronesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DronesApplication.class, args);
    }

    @Configuration
    @Profile("development")
    @ComponentScan(lazyInit = true)
    static class LocalConfig {
    }

}
