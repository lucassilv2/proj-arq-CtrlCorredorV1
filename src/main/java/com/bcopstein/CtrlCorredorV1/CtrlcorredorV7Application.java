package com.bcopstein.ctrlcorredor_v7_CLEAN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bcopstein" })
@EntityScan(basePackages = { "com.bcopstein" })
public class CtrlcorredorV7Application {

	public static void main(String[] args) {
		SpringApplication.run(CtrlcorredorV7Application.class, args);
	}

}
