package terminalcli;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import coreapi.Cafeteria;
import coreapi.InsufficientStockException;
import coreapi.Order;
import coreapi.OrderFactory;
import coreapi.OrderService;
import coreapi.Product;
import coreapi.ProductImpl;
import data.CafeteriaData;
import data.OrderData;
import data.ProductData;
import filepersistence.DiskCafeteriaData;
import filepersistence.DiskOrderData;
import filepersistence.DiskProductData;

public class Terminal {

	static boolean create;
	static String path = "./";
	static Cafeteria coffee;
	static CafeteriaData coffeeData;
	static OrderData orderData;
	static ProductData productData;
	static OrderService ordSer;
	
	
	public static void main(String[] args)
	{
		terminalParameters(args);

		coffeeData = new DiskCafeteriaData(path);
		orderData = new DiskOrderData(path);
		productData = new DiskProductData(path);
		ordSer = new OrderService(coffeeData, orderData, productData);
		
		
		createObjects();

		Screen cli = new Screen(coffee, ordSer);
		cli.main_screen();
	}

	
	private static void terminalParameters(String[] args)
	{
		if (args.length > 0)
		{
			if (args[0].equals("-c"))
			{
				create = true;
				if (args.length > 1)
				{
					path = args[1];
				}
			}
			else
			{
				path = args[0];
			}
		}
	}
	
	private static void createObjects()
	{		
		coffee = new Cafeteria(0, "Cafeteria ESI", "cafeteria@esi.uca.es");
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
		
		coffeeData.saveCafeteria(coffee);
	}
	
}
