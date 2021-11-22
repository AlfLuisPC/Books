package com.luis.books.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.luis.books.seguridad.LoginFilter;

@ServletComponentScan(basePackageClasses = LoginFilter.class)
@SpringBootApplication(scanBasePackages = {"com.luis"})
@EntityScan("com.luis.books.model")
@EnableJpaRepositories("com.luis.books.persistence")
@EnableAutoConfiguration
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	
}
