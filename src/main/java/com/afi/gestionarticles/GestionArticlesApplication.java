package com.afi.gestionarticles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.afi.gestionarticles.*"})
public class GestionArticlesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionArticlesApplication.class, args);
	}

}
