package com.springmicro.foodometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FoodometerApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodometerApiGatewayApplication.class, args);
	}

}
