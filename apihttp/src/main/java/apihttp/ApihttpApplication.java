package apihttp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import coreapi.*;
import data.*;
import filepersistence.*;

@SpringBootApplication
@Configuration
@RestController
@EnableSwagger2
@ComponentScan(basePackages =  {"coreapi","data","filepersistence","apihttp","com.example.accessingdatamysql"})
public class ApihttpApplication {

	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApihttpApplication.class, args);
	}
	
	@Bean
	public Cafeteria newcoffee()
	{
		return new Cafeteria(1,"Cafeteria ESI","coffeecruca@gmail.com");
	}
	
	@Bean
	public OrderData newOderData()
	{
		return new DiskOrderData("./");
	}
	
	@Bean
	public ProductData newProductData()
	{
		return new DiskProductData("./");
	}
	
	@Bean
	public UserData newUserData()
	{
		return new DiskUserData("./");
	}
	
	@Bean
	public OrderService newOrderService()
	{
		return new OrderService(new DiskCafeteriaData("./"),new DiskOrderData("./"), new DiskProductData("./"));
	}
	@Bean
	public CardData newCardData()
	{
		return new DiskCardData("./");
	}

}
