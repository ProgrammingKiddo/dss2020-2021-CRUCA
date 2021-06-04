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
import data.CafeteriaData;

public class DiskCafeteriaData implements CafeteriaData
{
	private String path;
	
	DiskCafeteriaData(String path)
	{
		this.path = path;
	}
	
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

	public List<Product> getProductsInStock(int cafeteriaId)
	{
		Cafeteria coffee = getCafeteria(cafeteriaId);
		return coffee.getAvailableProducts();
	}
	
	public int getProductStock(int cafeteriaId, Product prod)
	{
		Cafeteria coffee = getCafeteria(cafeteriaId);
		return coffee.getProductQuantity(prod);
		
	}
	
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
