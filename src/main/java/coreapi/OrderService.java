package coreapi;
//Falta libreria para linkedHashMap
public class OrderService
{
	
	/*---------------------------ORDER_PRODUCTS------------------------------------------*/
	
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
				if(q > 0 && q < mapProdQuant.get(actual))
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
				
				//Necesito acceder a la cantidad de producto que hay en la cesta
				
			}
		}
	
	}
	/*
	 * PRECONDITION:Receive an order and an id of a existing product, plus a positive quantity 
	 * POSTCONDITION:Eliminate the indicated amount of the product
	 */
	public void removeProductFromOrder(Order ord, int productId, int q)
	{
		//Necesito acceder a la cantidad del producto en la cesta
	}
	
	/* ---------------------------------ORDER_STATUS----------------------------------*/
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_InKitchen(Order ord)
	{
		//Debe haber productos en la lista
	}
	
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_Delivered(Order ord)
	{
		//El estado debe ser en cocina para poder ser entregado
	}
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_Charged(Order ord)
	{
		//El estado debe de ser entregado o en cocina para poder ser cobrado
	}
	
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_Finalized(Order ord)
	{
		//El estado debe ser cobrado para poder ser finalizada
	}
	
	/*-------------------------------DAILY_REGISTER-------------------------------*/
	
	/*
	 * PRECONDITION:Receive a date
	 * POSTCONDITION: Print on screen the number of orders and the total amount of 
	 * all orders for the indicated date
	 */
	public void checkDailyRegister(Fecha fech)
	{
		//Necesito acceder a una lista de todos los pedidos
	}
	
	
	
}
