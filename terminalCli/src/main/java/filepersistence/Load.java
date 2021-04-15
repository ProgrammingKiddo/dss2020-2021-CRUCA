package filepersistence;

import coreapi.Product;
import coreapi.ProductImpl;
import coreapi.ProductCatalog;
import coreapi.Cafeteria;
import coreapi.Order;
import coreapi.OrderImpl;

import java.io.*;

public class Load
{
	private String path;
	
	public Load(String path)
	{
		this.path = path;
	}
	
	public void LoadProducts()
	{
		try {
			FileInputStream products = new FileInputStream(path + "Products.txt");
			ObjectInputStream ProductRead = new ObjectInputStream(products);
			Product p = (Product)ProductRead.readObject();
			while(p != null)
			{
				//Guardar en memoria
				ProductCatalog.Instance().addProduct(p);
				p = (ProductImpl)ProductRead.readObject();
			}
			ProductRead.close();			
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void LoadCafeteria(Cafeteria coffee)
	{
		try {
			FileInputStream cafeterias = new FileInputStream(path + "Cafeterias.txt");
			ObjectInputStream CafeteriaRead = new ObjectInputStream(cafeterias);
			coffee = (Cafeteria)CafeteriaRead.readObject();
			CafeteriaRead.close();		
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void LoadOrders(Cafeteria coffee)
	{
		try {
			FileInputStream orders = new FileInputStream(path + "Orders.txt");
			ObjectInputStream OrderRead = new ObjectInputStream(orders);
			Order o = (Order)OrderRead.readObject();
			while(o != null)
			{
				coffee.registerOrder(o);
				//Guardar en memoria
				o = (OrderImpl)OrderRead.readObject();
			}
			OrderRead.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}