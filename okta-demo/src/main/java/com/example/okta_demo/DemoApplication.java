package com.example.okta_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner printEndpoints(ApplicationContext ctx) {
    return args -> {
      System.out.println("Registered endpoints:");
      ctx.getBeansOfType(SecurityFilterChain.class).forEach((name, bean) -> {
        System.out.println("SecurityFilterChain: " + name);
      });
    };
  }
}
