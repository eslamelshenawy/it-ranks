package com.itranks.selfservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.itranks.selfservices.config.RibbonConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@AutoConfigureAfter(RibbonAutoConfiguration.class)
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class SelfServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SelfServiceApplication.class, args);
	}

}
