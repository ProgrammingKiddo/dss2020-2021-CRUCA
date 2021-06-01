package filepersistence;


/**
 * @author María
 * @author Fran
 */


import coreapi.*;

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
		Product p = null;
		try 
		{
			FileInputStream products = new FileInputStream(path + "Product" + id + ".txt");
			ObjectInputStream ProductRead = new ObjectInputStream(products);
			p = (ProductImpl)ProductRead.readObject();
			ProductRead.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
		return p;	
	}
	
	public Cafeteria LoadCafeteria(int id)
	{
		Cafeteria coffee = null;
		try 
		{
			FileInputStream cafeterias = new FileInputStream(path + "Cafeteria" + id + ".txt");
			ObjectInputStream CafeteriaRead = new ObjectInputStream(cafeterias);
			coffee = (Cafeteria)CafeteriaRead.readObject();
			CafeteriaRead.close();		
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
		return coffee;
	}
	
	public Order LoadOrder(int id)
	{
		Order o = null;
		try {
			FileInputStream orders = new FileInputStream(path + "Order" + id + ".txt");
			ObjectInputStream OrderRead = new ObjectInputStream(orders);
			o = (OrderImpl)OrderRead.readObject();
			OrderRead.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
		return o;
	}
	
	public User LoadUser(int dni)
    {
		User u = null;
        try 
        {
            FileInputStream currentUser = new FileInputStream(path + "user" + dni + ".txt");
            ObjectInputStream UserRead = new ObjectInputStream(currentUser);
            u = (User)UserRead.readObject();
            UserRead.close();
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        return u;
    }
}