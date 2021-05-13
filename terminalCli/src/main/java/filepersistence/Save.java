package filepersistence;

import coreapi.Product;
import coreapi.Cafeteria;
import coreapi.Order;
import coreapi.ProductCatalog;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;


public class Save
{
	private String path;
	
	public Save(String path)
	{
		this.path = path;
	}
	
	public void SaveProduct(Product prod)
	{
		try 
		{
			FileOutputStream products = new FileOutputStream(path + "Product" + prod.getId() + ".txt");
			ObjectOutputStream ProductWrite = new ObjectOutputStream(products);
			ProductWrite.writeObject(prod);
			ProductWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void SaveCafeteria(Cafeteria coffee)
	{
		try {
			FileOutputStream cafeterias = new FileOutputStream(path + "Cafeteria" + coffee.getId() + ".txt");
			ObjectOutputStream CafeteriaWrite = new ObjectOutputStream(cafeterias);
			CafeteriaWrite.writeObject(coffee);
			CafeteriaWrite.close();			
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void SaveOrder(Order ord)
	{
		try {
			FileOutputStream orders = new FileOutputStream(path + "Order" + ord.getId() + ".txt");
			ObjectOutputStream OrderWrite = new ObjectOutputStream(orders);
			OrderWrite.writeObject(ord);
			OrderWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void SaveUser(User user)
	{
		try {
			FileOutputStream currentUser = new FileOutputStream(path + "user" + user.getDni() + ".txt");
			ObjectOutputStream UserWrite = new ObjectOutputStream(currentUser);
			UserWrite.writeObject(user);
			UserWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	
}