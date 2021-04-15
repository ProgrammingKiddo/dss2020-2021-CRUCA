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
import filepersistence.Load;
import filepersistence.Save;
import coreapi.ProductCatalog;

public class Terminal {

	static boolean create;
	static String path = "./";
	static Cafeteria coffee;

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		terminalParameters(args);
		System.out.println(path);
		if (create == true)
		{
			createObjects();
		}
		else
		{
			loadObjects();
		}
		Screen terminal = new Screen();
		terminal.main_screen(coffee);
		
		saveObjects();
	}

	
	private static void terminalParameters(String[] args)
	{
		if (args.length > 0)
		{
			if (args[0].equals("-c"))
			{
				create = true;
				if (args.length >= 2)
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
		OrderService ordSer = new OrderService();
		coffee = new Cafeteria(0, "Cafeteria ESI");
		coffee.AddType("Menu");
		coffee.AddType("Comida");
		coffee.AddType("Bebida");
		
		ProductCatalog.Instance().addProduct(new ProductImpl(1, new BigDecimal(1.7), "Bacon-queso-huevo", "Menu"));
		ProductCatalog.Instance().addProduct(new ProductImpl(0, new BigDecimal(1.2), "Patatas fritas", "Comida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(2, new BigDecimal(0.9), "Café con leche", "Bebida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(3, new BigDecimal(0.5), "Doritos", "Comida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(4, new BigDecimal(1.6), "Monster", "Bebida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(5, new BigDecimal(1.3), "Bocadillo de tortilla", "Comida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(6, new BigDecimal(0.8), "Botella de agua", "Bebida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(7, new BigDecimal(0.7), "Donut blanco", "Comida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(8, new BigDecimal(0.75), "Donut de chocolate", "Comida"));
		ProductCatalog.Instance().addProduct(new ProductImpl(9, new BigDecimal(1.4), "Sándwich de roquefort", "Menu"));
		
		coffee.registerProduct(ProductCatalog.Instance().getProduct(0), 30);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(1), 10);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(2), 5);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(3), 15);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(4), 20);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(5), 50);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(6), 10);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(7), 8);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(8), 12);
		coffee.registerProduct(ProductCatalog.Instance().getProduct(9), 20);
		
		Order order1 = OrderFactory.createOrder(coffee);
		Order order2 = OrderFactory.createOrder(coffee);
		Order order3 = OrderFactory.createOrder(coffee);
		Order order4 = OrderFactory.createOrder(coffee, LocalDateTime.of(2021, 2, 23, 14, 0));
		try {
			ordSer.addProductToOrder(coffee, order1, 0, 5);
			ordSer.addProductToOrder(coffee, order1, 1, 1);
			ordSer.addProductToOrder(coffee, order1, 9, 7);
			ordSer.addProductToOrder(coffee, order2, 5, 15);
			ordSer.addProductToOrder(coffee, order3, 3, 4);
			ordSer.addProductToOrder(coffee, order3, 3, 5);
			ordSer.addProductToOrder(coffee, order4, 7, 2);
		} catch (InsufficientStockException ex)
		{
			//...
		}
		
	}
	
	private static void loadObjects()
	{
		System.out.println("Loading objects...");
		Load fileLoader = new Load(path);
		fileLoader.LoadProducts();
		System.out.println("Objects loaded!");
		fileLoader.LoadCafeteria(coffee);
		fileLoader.LoadOrders(coffee);
	}
	
	private static void saveObjects()
	{
		Save fileSaver = new Save(path);
		fileSaver.SaveProducts();
		fileSaver.SaveCafeteria(coffee);
		fileSaver.SaveOrders(coffee);
	}
}
