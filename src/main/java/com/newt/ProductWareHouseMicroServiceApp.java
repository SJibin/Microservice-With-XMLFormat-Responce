package com.newt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableEurekaClient
@EnableSwagger2
public class ProductWareHouseMicroServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(ProductWareHouseMicroServiceApp.class, args);

	}

}
