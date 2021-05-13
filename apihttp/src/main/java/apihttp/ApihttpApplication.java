package apihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "coreapi")
public class ApihttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApihttpApplication.class, args);
	}

}
