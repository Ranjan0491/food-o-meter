package com.springmicro.foodometer.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableEurekaClient
@Configuration
public class LocalDiscovery {

    /*
    For Spring Boot >= 1.4
    Spring Boot no longer automatically defines a RestTemplate but instead defines a RestTemplateBuilder
    allowing you more control over the RestTemplate that gets created. You can inject the
    RestTemplateBuilder as an argument in your @Bean method to create a RestTemplate.

    The @LoadBalanced annotation is required for your RestTemplate to notify that everytime you call a service
    using RestTemplate it has to fetch the service details from Eureka server with the given service name.
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
