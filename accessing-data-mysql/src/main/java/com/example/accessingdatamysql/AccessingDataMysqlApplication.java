package com.example.accessingdatamysql;

/**
 * Class that assembles the structure of the objects.
 * @author Fran
 * @author Maria
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@Configuration
@RestController
@EnableSwagger2
@ComponentScan(basePackages =  {"coreapi","data","filepersistence","apihttp"})

public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}
	
	@Bean
	public JavaMailSender newMailService()
	{
		return new JavaMailSenderImpl();
	}

}
