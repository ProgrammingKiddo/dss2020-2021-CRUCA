package coreapi;

import java.util.Date;


public class OrderService
{
	//Create a new object of OrderService
	public OrderService() {}

	
	/*---------------------------ORDER_PRODUCTS------------------------------------------*/
	
	/*
	 * PRECONDITION:Receive an order and an id of a existing product, plus a positive quantity 
	 * POSTCONDITION: Add the product with the indicated quantity to the order
	 */
	public void addProductToOrder(Cafeteria coffe, OrderImpl ord, int productId, int q)
	{
		Product prod = ProductCatalog.getProduct(productId);
		if(ord.containsProduct(productId))
		{
			throw new RuntimeException("This product is already in your basket, you can modify the quantity of it if you wish.");
		}
		else
		{
			if(coffe.productStock.containsKey(prod))
			{
				if(q > 0 && coffe.productStock.get(prod).intValue() >= q)
				{
					ord.addProduct(productId,q);
				}
				else
				{
					throw new RuntimeException("There is not enough stock of the product.");
				}
			}
		}
		
	}
	
	/*
	 * PRECONDITION:Receive an order and an id of a existing product, plus a positive quantity 
	 * POSTCONDITION: Modify the quantity of the product indicated in the order
	 */
	public void modifyProductQuantity(Cafeteria coffe, OrderImpl ord, int productId, int q)
	{
		Product prod = ProductCatalog.getProduct(productId);
		
		if(coffe.productStock.containsKey(prod) && ord.containsProduct(productId))
		{
			if(q > 0 && coffe.productStock.get(prod).intValue() >= q)
			{
				ord.addProduct(productId,q);
			}
			else
			{
				throw new RuntimeException("There is not enough stock of the product.");
			}
		}
		else
		{
			throw new RuntimeException("The product is not in your basket.");
		}
	
	}
	/*
	 * PRECONDITION:Receive an order and an id of a existing product, plus a positive quantity 
	 * POSTCONDITION:Eliminate the indicated amount of the product
	 */
	public void removeProductFromOrder(OrderImpl ord, int productId, int q)
	{
		int quantbasket = ord.checkProductQuantity(productId);
		if(ord.containsProduct(productId))
		{
			if(q > 0 && q <= quantbasket)
			{
				ord.removeProduct(productId, q);
			}
			else
			{
				throw new RuntimeException("Can't remove that amount of product.");
			}
		}	
		else
		{
			throw new RuntimeException("This object is not in your basket.");
		}
		
	}
	
	/* ---------------------------------ORDER_STATUS----------------------------------*/
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_InKitchen(OrderImpl ord)
	{
		//There must be products in the basket and the order be in the open state
		if(!ord.getProducts().isEmpty() && ord.getStatus() == OrderStatus.OPEN)
		{
			ord.setStatus(OrderStatus.IN_KITCHEN);
		}
		else
		{
			throw new RuntimeException("The order has no products to send to the kitchen.");
		}
	}
	
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_Delivered(OrderImpl ord)
	{
		//The state must be in the kitchen to be delivered
		if(ord.getStatus() == OrderStatus.IN_KITCHEN)
		{
			ord.setStatus(OrderStatus.DELIVERED);
		}
		else
		{
			throw new RuntimeException("The order cannot be entered if it has not been in the kitchen.");
		}
	}
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_Payed(OrderImpl ord)
	{
		//The state must be delivered or in the kitchen in order to be paid
		if(ord.getStatus() == OrderStatus.IN_KITCHEN || ord.getStatus() == OrderStatus.DELIVERED)
		{
			ord.setStatus(OrderStatus.PAYED);
		}
		else
		{
			throw new RuntimeException("The order cannot be charged because it is not yet in the kitchen or delivered.");
		}
		
	}
	
	/*
	 * PRECONDITION:Receive an order
	 * POSTCONDITION: Assign the status to the order
	 */
	public void OrderStatus_Finished(OrderImpl ord)
	{
		// The state must be charged in order to be finalized
		if(ord.getStatus() == OrderStatus.PAYED)
		{
			ord.setStatus(OrderStatus.FINISHED);
		}
		else
		{
			throw new RuntimeException("The order cannot be finalized because it has not been charged.");
		}
		
	}
	
	/*-------------------------------DAILY_REGISTER-------------------------------*/
	
	/*
	 * PRECONDITION: Receive a date
	 * POSTCONDITION: Return the total of all orders for the date entered.
	 */
	public float DailyRegister(Cafeteria coffe, Date date)
	{
		float total = 0; //We save the total of the day
		
		//We go through the order history of the establishment
		for(Order ord: coffe.orderHistory)
		{
			if(ord.getDate() == date)
			{
				total += ord.totalCost();
			}
		}
		return total;
	}
	
	
	
}
