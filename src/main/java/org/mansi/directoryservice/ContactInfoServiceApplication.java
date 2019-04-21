package org.mansi.directoryservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ContactInfoServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ContactInfoServiceApplication.class, args);
	}

}
