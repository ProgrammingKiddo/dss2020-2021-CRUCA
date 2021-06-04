package data;

/**
 * 
 * @author Borja
 *
 */

import coreapi.Cafeteria;
import coreapi.Product;
import java.util.List;

public interface CafeteriaData {

	public Cafeteria getCafeteria(int cafeteriaId);
	public List<Product> getProductsInStock(int cafeteriaId);
	public int getProductStock(int cafeteriaId, Product prod);
	public void saveCafeteria(Cafeteria coffee);
}
