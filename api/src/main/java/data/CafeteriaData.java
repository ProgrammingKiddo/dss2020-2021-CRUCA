package data;

import coreapi.Cafeteria;
import coreapi.Product;
import java.util.List;

public interface CafeteriaData {

	public Cafeteria getCafeteria(int id);
	public void saveCafeteria(Cafeteria cafeteriaToSave);
	public List<Product> getProductsInStock();
	public int getProductStock(int productId);
	public void modifyProductStock(int newStock);
}
