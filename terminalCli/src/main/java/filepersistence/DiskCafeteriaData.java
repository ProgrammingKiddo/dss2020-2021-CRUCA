package filepersistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 
 * @author Borja
 *
 */

import coreapi.Cafeteria;
import coreapi.Product;
import coreapi.User;
import data.CafeteriaData;

/** 
 * This class represent the functions for load or save the Cafeteria objects using files and other necessary data 
 * @author Maria
 * @author Fran
 * @author Borja
 */

public class DiskCafeteriaData implements CafeteriaData
{
	private String path;
	
	public DiskCafeteriaData(String path)
	{
		this.path = path;
	}
	
	/**
	 * Obtain a specific coffee shop by his id
	 * 
	 * @param cafeteriaId	Identifier of a coffee shop
	 * @see Cafeteria
	 * @return 		Return the specific Cafeteria object searched
	 */
	public Cafeteria getCafeteria(int cafeteriaId)
	{
		Cafeteria coffee = null;
		
		try 
		{
			FileInputStream cafeterias = new FileInputStream(path + "Cafeteria" + cafeteriaId + ".txt");
			ObjectInputStream CafeteriaRead = new ObjectInputStream(cafeterias);
			coffee = (Cafeteria)CafeteriaRead.readObject();
			CafeteriaRead.close();		
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
		
		return coffee;
	}

	/**
	 * Obtain a list of the available products of a specific Cafeteria
	 * 
	 * @param cafeteriaId	Identifier of a coffee shop from which we will get the products in stock
	 * @see Cafeteria
	 * @see Product
	 * @return List of available Products in the Cafeteria.
	 */
	public List<Product> getProductsInStock(int cafeteriaId)
	{
		Cafeteria coffee = getCafeteria(cafeteriaId);
		return coffee.getAvailableProducts();
	}
	
	/**
	 * Obtain the amount of a specific Product in a specific Cafeteria
	 * 
	 * @param cafeteriaId	Identifier of a coffee shop from which we will get the amount of the products in stock
	 * @param prod			Specific Product of which we want to know its quantity
	 * @see Cafeteria
	 * @see Product
	 * @return Amount of available specific product
	 */
	public int getProductStock(int cafeteriaId, Product prod)
	{
		Cafeteria coffee = getCafeteria(cafeteriaId);
		return coffee.getProductQuantity(prod);
		
	}
	
	/**
	 * Save in a file a Cafeteria object
	 * 
	 * @param coffeee	Cafeteria object will be saved
	 * @see Cafeteria
	 */
	public void saveCafeteria(Cafeteria coffee)
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
	
}
