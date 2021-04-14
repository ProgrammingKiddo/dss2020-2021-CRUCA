package terminalcli;
import coreapi;
import java.io.*;

public class Load
{
	public void LoadProducts()
	{
		FileInputStream products = new FileInputStream("Products.txt");
		ObjectInputStream ProductRead = new ObjectInputStream(products);
		Product p = (Product)ProductRead.readObject();
		while(p != null)
		{
			//Guardar en memoria
			p = (Product)ProductRead.readObject();
		}
		ProductRead.close();
	}
	
	public void LoadCafeterias()
	{
		FileInputStream cafeterias = new FileInputStream("Cafeterias.txt");
		ObjectInputStream CafeteriaRead = new ObjectInputStream(cafeterias);
		Cafeteria c = (Cafeteria)CafeteriaRead.readObject();
		while(c != null)
		{
			//Guardar en memoria
			c = (Cafeteria)CafeteriaRead.readObject();
		}
		CafeteriaRead.close();
	}
	
	public void LoadOrders()
	{
		FileInputStream orders = new FileInputStream("Orders.txt");
		ObjectInputStream OrderRead = new ObjectInputStream(orders);
		Order o = (Order)OrderRead.readObject();
		while(p != null)
		{
			//Guardar en memoria
			o = (Order)OrderRead.readObject();
		}
		OrderRead.close();
	}
}