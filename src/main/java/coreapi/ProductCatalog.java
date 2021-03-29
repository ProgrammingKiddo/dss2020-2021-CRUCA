/**
 * The <code>ProductCatalog</code> class works as a placeholder for product
 * data representation, as it will come later pre-established as a file.
 * @author Borja
 * @version 0.2
 * @see Product
 */

package coreapi;


public class ProductCatalog {

	private static Product[] products = new Product[10];
	private static ProductCatalog uniqueInstance;
	
	/**
	 * Returns the unique existing instance of the <code>ProductCatalog</code> class,
	 * through which the user is able to call its methods.
	 * @return the only instance of the class.
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
	/**
	 * Returns the corresponding reference to a certain product, determined by its identifier.
	 * @param id the product identifier number to check for.
	 * @return the product corresponding to the id passed as a parameter.
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
