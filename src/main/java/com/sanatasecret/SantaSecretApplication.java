package com.sanatasecret;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sanatasecret.*"})
public class SantaSecretApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantaSecretApplication.class, args);
	}

}
