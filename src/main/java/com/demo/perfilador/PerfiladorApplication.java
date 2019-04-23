package com.demo.perfilador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PerfiladorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfiladorApplication.class, args);
	}

}
