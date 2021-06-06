package apihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configuration
@RestController
@ComponentScan(basePackages = "coreapi")
public class ApihttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApihttpApplication.class, args);
	}

}
