package coreapi;
//Falta libreria para linkedHashMap
public class OrderService
{
	/*
	 * PRECONDITION:Receive an order and an id of a existing product, plus a positive quantity 
	 * POSTCONDITION: Add the product with the indicated quantity to the order
	 */
	public void addProductToOrder(Order ord, int productId, int q)
	{
		
		//Suponiendo que tenemos un mapa de producto con su cantidad (en cada restaurante) = mapProdQuant
		Iterator<Product> prodIterator = mapProdQuant.iterator();
		while(prodIterator.hasNext())
		{
			Product actual = prodIterator.next();
			if(actual.getId() == productId)
			{
				/*
				 * We check that the quantity of the product to be introduced is valid, 
				 * that is, that it is greater than 0 and that there is enough stock
				 */
				if(q > 0 && q < linkedHashMap.get(actual))
				{
					ord.addProduct(actual,q);
				}
				else
				{
					System.out.println("Insufficient stock");
				}
			}
		
		}
		
	}
	
	/*
	 * PRECONDITION:Receive an order and an id of a existing product, plus a positive quantity 
	 * POSTCONDITION: Modify the quantity of the product indicated in the order
	 */
	public void modifyProductQuantity(Order ord, int productId, int q)
	{
		Iterator<Product> prodIterator = mapProdQuant.iterator();
	
		
		while(prodIterator.hasNext())
		{
			Product actual = prodIterator.next();
			if(actual.getId() == productId)
			{
				/*
				 * We check that the quantity of the product to be introduced is valid, 
				 * that is, that it is greater than 0 and that there is enough stock
				 */
				
				
			}
	}
}
