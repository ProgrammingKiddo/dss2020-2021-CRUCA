package coreapi;

public class OrderOperator
{
	/*
	 * PRECONDITION:Recibe an order and an existing product, plus a positive quantity 
	 * POSTCONDITION: Add the product with the indicated quantity to the order
	 */
	public void addProductToOrder(Order ord, Product prod, int q)
	{
		/*
		 * We check that the quantity of the product to be introduced is valid, 
		 * that is, that it is greater than 0 and that there is enough stock
		 */
		if(q > 0 && q < prod.getStock())
		{
			ord.addProduct(prod,q);
		}
	}
	
	/*
	 * PRECONDITION:Recibe an order and an existing product, plus a positive quantity 
	 * POSTCONDITION: Modify the quantity of the product indicated in the order
	 */
	public void modifyProductQuantity(Order ord, Product prod, int q)
	{
		/*
		 * We check that the quantity of the product to be introduced is valid, 
		 * that is, that it is greater than 0 and that there is enough stock
		 */
		if(q > 0 && q < prod.getStock())
		{
			ord.addProduct(prod,q);
		}
	}
	
	/*
	 * PRECONDITION:Recibe an order and an existing product, plus a positive quantity 
	 * POSTCONDITION:Eliminate the indicated amount of the product
	 */
		public void removeProductFromOrder(Order ord, Product prod, int q)
		{
			if(q > 0)
			{
				removeProduct(prod,q); //DUDA
			}
		}
		
		
}


	
	
