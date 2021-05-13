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
	
	public Product LoadProduct(int id)
	{
		try 
		{
			FileInputStream products = new FileInputStream(path + "Product" + id + ".txt");
			ObjectInputStream ProductRead = new ObjectInputStream(products);
			Product p = (ProductImpl)ProductRead.readObject();
			ProductRead.close();
			return p;	
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public Cafeteria LoadCafeteria(int id)
	{
		try 
		{
			FileInputStream cafeterias = new FileInputStream(path + "Cafeteria" + id + ".txt");
			ObjectInputStream CafeteriaRead = new ObjectInputStream(cafeterias);
			Cafeteria coffee = (Cafeteria)CafeteriaRead.readObject();
			CafeteriaRead.close();		
			return coffee;
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public Order LoadOrder(int id)
	{
		try {
			FileInputStream orders = new FileInputStream(path + "Order" + id + ".txt");
			ObjectInputStream OrderRead = new ObjectInputStream(orders);
			Order o = (OrderImpl)OrderRead.readObject();
			OrderRead.close();
			return o;
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	
	public User LoadUser(int dni)
    {
        try 
        {
            FileInputStream currentUser = new FileInputStream(path + "user" + dni + ".txt");
            ObjectInputStream UserRead = new ObjectInputStream(currentUser);
            User u = (User)UserRead.readObject();
            UserRead.close();
            return u;
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}