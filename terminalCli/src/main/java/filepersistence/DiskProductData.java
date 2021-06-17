package filepersistence;

/** 
 * This class represent the functions for load or save the Products objects using files
 * @author Maria
 * @author Fran
 * @author Borja
 */

import data.ProductData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import coreapi.Product;
import coreapi.ProductImpl;


public class DiskProductData implements ProductData
{
	private String path;
	
	public DiskProductData(String path)
	{
		this.path = path;
	}
	
	/**
	 * Obtain a specific Product by his id
	 * 
	 * @param id	Identifier of an product
	 * @see Product
	 * @return 		Return the specific Product object searched
	 */
	public Product getProduct(int id)
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
	
	/**
	 * Save in a file an Product object
	 * 
	 * @param prod	Product object will be saved
	 * @see Product
	 */
	public void saveProduct(Product prod)
	{
		try {
			FileOutputStream products = new FileOutputStream(path + "Product" + prod.getId() + ".txt");
			
			ObjectOutputStream ProductWrite = new ObjectOutputStream(products);
			ProductWrite.writeObject(prod);
			ProductWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
