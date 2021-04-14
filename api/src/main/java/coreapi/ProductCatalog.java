package coreapi;

import java.math.BigDecimal;

/**
 * This class works as a placeholder for product data representation,
 * as it will come later pre-established as a file.
 * @author Borja
 * @version 0.2
 * @see Product
 */
public class ProductCatalog {

	private static Product[] products = new Product[10];
	private static ProductCatalog uniqueInstance;
	
	/**
	 * Returns the unique existing instance of the <code>ProductCatalog</code> class,
	 * through which the user is able to call its methods.
	 * @return	The only instance of the class.
	 */
	public static ProductCatalog Instance()
	{
		if (uniqueInstance == null)
		{
			uniqueInstance = new ProductCatalog();
		}
		return uniqueInstance;
	}
	/**
	 * This private constructor initializes the list of products that serves as a
	 * temporary placeholder for product information.
	 */
	private ProductCatalog()
	{
		products[0] = new ProductImpl(0, new BigDecimal(1.2), "Patatas fritas", "Comida");
		products[1] = new ProductImpl(1, new BigDecimal(1.7), "Bacon-queso-huevo","Menu");
		products[2] = new ProductImpl(2, new BigDecimal(0.9), "Café con leche","Bebida");
		products[3] = new ProductImpl(3, new BigDecimal(0.5), "Doritos","Comida");
		products[4] = new ProductImpl(4, new BigDecimal(1.6), "Monster","Bebida");
		products[5] = new ProductImpl(5, new BigDecimal(1.3), "Bocadillo de tortilla","Comida");
		products[6] = new ProductImpl(6, new BigDecimal(0.8), "Botella de agua","Bebida");
		products[7] = new ProductImpl(7, new BigDecimal(0.7), "Donut blanco","Comida");
		products[8] = new ProductImpl(8, new BigDecimal(0.75), "Donut de chocolate","Comida");
		products[9] = new ProductImpl(9, new BigDecimal(1.4), "Sándwich de roquefort","Menu");
	}
	/**
	 * Returns the corresponding reference to a certain product, determined by its identifier.
	 * @param id	The product identifier number to check for.
	 * @return	The product corresponding to the id passed as a parameter.
	 */
	public Product getProduct(int id)
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