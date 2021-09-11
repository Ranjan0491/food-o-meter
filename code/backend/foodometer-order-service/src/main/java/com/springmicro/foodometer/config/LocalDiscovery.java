package com.springmicro.foodometer.config;

import com.netflix.discovery.EurekaClient;
import com.springmicro.foodometer.constants.FoodOrderConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@EnableDiscoveryClient
@EnableEurekaClient
@Configuration
public class LocalDiscovery {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    /*
    For Spring Boot >= 1.4
    Spring Boot no longer automatically defines a RestTemplate but instead defines a RestTemplateBuilder
    allowing you more control over the RestTemplate that gets created. You can inject the
    RestTemplateBuilder as an argument in your @Bean method to create a RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @PostConstruct
    public void registerEurekaApplications() throws Exception {
        eurekaClient.getApplication(FoodOrderConstants.FOOD_ITEM_SERVICE_NAME);
        discoveryClient.getInstances(FoodOrderConstants.FOOD_ITEM_SERVICE_NAME);
    }
}
