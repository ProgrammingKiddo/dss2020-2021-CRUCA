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
	
	public void SaveProducts()
	{
		try {
			FileOutputStream products = new FileOutputStream(path + "Products.txt");
			ObjectOutputStream ProductWrite = new ObjectOutputStream(products);
			int i = 0;
			Product p = ProductCatalog.Instance().getProduct(i);
			while (p != null)
			{
				ProductWrite.writeObject(p);
				i++;
				p = ProductCatalog.Instance().getProduct(i);
			}
			ProductWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void SaveCafeteria(Cafeteria coffee)
	{
		try {
			FileOutputStream cafeterias = new FileOutputStream(path + "Cafeterias.txt");
			ObjectOutputStream CafeteriaWrite = new ObjectOutputStream(cafeterias);
			CafeteriaWrite.writeObject(coffee);
			CafeteriaWrite.close();			
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public void SaveOrders(Cafeteria coffee)
	{
		try {
			FileOutputStream orders = new FileOutputStream(path + "Orders.txt");
			ObjectOutputStream OrderWrite = new ObjectOutputStream(orders);

			List<Order> coffeeOrders = coffee.getOrders();
			Iterator<Order> orderIterator = coffeeOrders.iterator();

			while (orderIterator.hasNext())
			{
				OrderWrite.writeObject(orderIterator.next());
			}
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