package coreapi;


public class ProductCatalog {

	private static Product[] products = new Product[10];
	
	public static void createProducts()
	{
		products[0] = new ProductImpl(0, (float) 1.2, "Patatas fritas");
		products[1] = new ProductImpl(1, (float) 1.7, "Bacon-queso-huevo");
		products[2] = new ProductImpl(2, (float) 0.9, "Café con leche");
		products[3] = new ProductImpl(3, (float) 0.5, "Doritos");
		products[4] = new ProductImpl(4, (float) 1.6, "Monster");
		products[5] = new ProductImpl(5, (float) 1.3, "Bocadillo de tortilla");
		products[6] = new ProductImpl(6, (float) 0.8, "Botella de agua");
		products[7] = new ProductImpl(7, (float) 0.7, "Donut blanco");
		products[8] = new ProductImpl(8, (float) 0.75, "Donut de chocolate");
		products[9] = new ProductImpl(9, (float) 1.4, "Sándwich de roquefort");
	}
	
	public static Product getProduct(int id)
	{
		if (id <= 9 && id >= 0)
		{
			return products[id];
		}
		else
		{
			return null;
		}
	}
}
