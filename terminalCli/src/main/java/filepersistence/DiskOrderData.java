package filepersistence;

/** 
 * This class represent the functions for load or save the Order objects using files
 * @author Maria
 * @author Fran
 * @author Borja
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import coreapi.Order;
import coreapi.OrderImpl;
import coreapi.User;
import data.OrderData;

public class DiskOrderData implements OrderData
{
	private String path;
	
	public DiskOrderData(String path)
	{
		this.path = path;
	}
	
	/**
	 * Obtain a specific Order by his id
	 * 
	 * @param dni	Identifier of an Order
	 * @see Order
	 * @return 		Return the specific Order object searched
	 */
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
	
	/**
	 * Save in a file an User object
	 * 
	 * @param orderToSave	Order object will be saved
	 * @see Order
	 */
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
