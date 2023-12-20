package com.tavara.InterfazFinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExamenParcialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenParcialApplication.class, args);
	}

}
