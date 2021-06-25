package com.example.accessingdatamysql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import coreapi.Cafeteria;
import coreapi.Card;
import coreapi.InsufficientStockException;
import coreapi.Order;
import coreapi.OrderFactory;
import coreapi.OrderService;
import coreapi.Product;
import coreapi.ProductImpl;
import coreapi.User;
import data.CafeteriaData;
import data.CardData;
import data.OrderData;
import data.ProductData;
import data.UserData;
import filepersistence.DiskCafeteriaData;
import filepersistence.DiskCardData;
import filepersistence.DiskOrderData;
import filepersistence.DiskProductData;
import filepersistence.DiskUserData;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@Configuration
@RestController
@EnableSwagger2
@ComponentScan(basePackages =  {"coreapi","data","filepersistence","apihttp"})

public class AccessingDataMysqlApplication {

	private static boolean create = false;

	public static void main(String[] args) {
		if (args.length > 0)
		{
			if (args[0].equalsIgnoreCase("-c"))
			{
				create = true;
			}
		}
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}
	
	@Bean
	public Cafeteria newcoffee()
	{
		Cafeteria coffee;
		OrderData orderData = new DiskOrderData("./");
		CafeteriaData coffeeData = new DiskCafeteriaData("./");
		
		if (create == true)
		{
			ProductData productData = new DiskProductData("./");
			OrderService ordSer = new OrderService(coffeeData, orderData, productData);
			coffee = new Cafeteria(0, "Cafeteria ESI", "coffeecruca@gmail.com");
			coffee.AddType("Menu");
			coffee.AddType("Comida");
			coffee.AddType("Bebida");
			
			List<Product> productList = new ArrayList<Product>();
			
			productList.add(new ProductImpl(0, new BigDecimal(1.2), "Patatas fritas", "Comida"));
			productList.add(new ProductImpl(1, new BigDecimal(1.7), "Bacon-queso-huevo", "Menu"));
			productList.add(new ProductImpl(2, new BigDecimal(0.9), "Café con leche", "Bebida"));
			productList.add(new ProductImpl(3, new BigDecimal(0.5), "Doritos", "Comida"));
			productList.add(new ProductImpl(4, new BigDecimal(1.6), "Monster", "Bebida"));
			productList.add(new ProductImpl(5, new BigDecimal(1.3), "Bocadillo de tortilla", "Comida"));
			productList.add(new ProductImpl(6, new BigDecimal(0.8), "Botella de agua", "Bebida"));
			productList.add(new ProductImpl(7, new BigDecimal(0.7), "Donut blanco", "Comida"));
			productList.add(new ProductImpl(8, new BigDecimal(0.75), "Donut de chocolate", "Comida"));
			productList.add(new ProductImpl(9, new BigDecimal(1.4), "Sándwich de roquefort", "Menu"));
			
			for (Product prod : productList)
			{
				productData.saveProduct(prod);
				coffee.registerProduct(prod, 20);
			}
			
			
			Order order1 = OrderFactory.createOrder(coffee);
			Order order2 = OrderFactory.createOrder(coffee);
			Order order3 = OrderFactory.createOrder(coffee);
			Order order4 = OrderFactory.createOrder(coffee, LocalDateTime.of(2021, 2, 23, 14, 0));
			try {
				ordSer.addProductToOrder(coffee, order1, productList.get(0), 5);
				ordSer.addProductToOrder(coffee, order1, productList.get(1), 1);
				ordSer.addProductToOrder(coffee, order1, productList.get(9), 7);
				ordSer.addProductToOrder(coffee, order2, productList.get(5), 15);
				ordSer.addProductToOrder(coffee, order3, productList.get(3), 4);
				ordSer.addProductToOrder(coffee, order3, productList.get(3), 5);
				ordSer.addProductToOrder(coffee, order4, productList.get(7), 2);
			} catch (InsufficientStockException ex)
			{
				//...
			}
			
			orderData.saveOrder(order1);
			orderData.saveOrder(order2);
			orderData.saveOrder(order3);
			orderData.saveOrder(order4);
			
			User pruebaUser = new User("Juan", "Gomez", 4000, LocalDate.now(), 320, "correo@correo.es");
			UserData userData = new DiskUserData("./");
			userData.saveUser(pruebaUser);
			
			CardData cardData = new DiskCardData("./");
			Card tarjetaUser = new Card(320, 4000);
			cardData.saveCard(tarjetaUser);
			
			coffeeData.saveCafeteria(coffee);
		}
		else
		{
			// Si no indicamos el modo creacion, tomamos la primera cafetería existente por defecto,
			// y el punto de partida del contador de la factoría de pedidos en base al número
			// de pedidos ya existentes.
			coffee = coffeeData.getCafeteria(0);
			OrderFactory.setOrderCount(orderData.getNumberOfExistingOrders());
		}
		
		return coffee;
	}
	@Bean
	public JavaMailSender newMailService()
	{
		return new JavaMailSenderImpl();
	}

}
