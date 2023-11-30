package com.example.demoreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class DemoReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoReactiveApplication.class, args);
	}

}
