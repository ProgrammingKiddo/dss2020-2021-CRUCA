package filepersistence;

/** 
 * @author Borja
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import coreapi.Order;
import coreapi.OrderImpl;
import data.OrderData;

public class DiskOrderData implements OrderData
{
	private String path;
	
	DiskOrderData(String path)
	{
		this.path = path;
	}
	
	public Order getOrder(int id)
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
	
	public void saveOrder(Order orderToSave)
	{
		try {
			FileOutputStream orders = new FileOutputStream(path + "Order" + orderToSave.getId() + ".txt");
			ObjectOutputStream OrderWrite = new ObjectOutputStream(orders);
			OrderWrite.writeObject(orderToSave);
			OrderWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
