package terminalcli;
import java.io.ObjectOutputStream;

import coreapi;

public class Save
{
	public void SaveProducts()
	{
		FileOutputStream products = new FileOutputStream("Products.txt");
		ObjectOutputStream ProductWrite = new ObjectOutputStream(products);
		//Obtener lista de productos en memoria
		//Recorrer lista y
		{
			Product p = //Producto de la lista
			ProductWrite.writeObject(p);
		}
		ProductWrite.close();
	}
	
	public void SaveCafeterias()
	{
		FileOutputStream cafeterias = new FileOutputStream("Cafeterias.txt");
		ObjectInputStream CafeteriaRead = new ObjectInputStream(cafeterias);
		Cafeteria c = (Cafeteria)CafeteriaRead.readObject();
		while(c != null)
		{
			//Guardar en memoria
			c = (Cafeteria)CafeteriaRead.readObject();
		}
		CafeteriaRead.close();
	}
	
	public void SaveOrders()
	{
		FileOutputStream orders = new FileOutputStream("Orders.txt");
		ObjectOutputStream OrderRead = new ObjectOutputStream(orders);
		List
		while(p != null)
		{
			//Guardar en memoria
			o = (Order)OrderRead.readObject();
		}
		OrderRead.close();
	}
}