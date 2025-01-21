package com.forohub.sebas.giraldo_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 */
@SpringBootApplication(scanBasePackages = "com.forohub.sebas.giraldo_2")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
