package filepersistence;

/** 
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