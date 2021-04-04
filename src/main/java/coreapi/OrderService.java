package coreapi;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class acts as a service provider and interface for the client to work
 * with objects from the Order class.
 * Modifying and operating an object of the order class should always be done
 * through the methods provided here.
 *  @author Maria
 *  @author Borja
 *  @version 0.2
 */
public class OrderService
{
	//Create a new object of OrderService
	public OrderService() {}

	
	/*---------------------------ORDER_PRODUCTS------------------------------------------*/
	
	/**
	 * Receive an order and an id of a existing product, plus a positive quantity 
	 * Add the product with the indicated quantity to the order
	 * @param coffe 		the Cafeteria which stock the different orders.
	 * @param ord 			the order which stock the different products and the quantities.
	 * @param productId 	the id of the product which will be add to the order.
	 * @param quantity 		the quantity of the product.
	 * @throws InsufficientStockException	If there isn't enough stock of the product
	 */
	public void addProductToOrder(Cafeteria coffe, OrderImpl ord, int productId, int quantity) throws InsufficientStockException
	{
		Product prod = ProductCatalog.Instance().getProduct(productId);
		if(coffe.getAvailableProducts().contains(prod))
		{
			if(quantity > 0 && coffe.getProductQuantity(prod) >= quantity)
			{
				ord.addProduct(productId, quantity);
			}
			else
			{
				throw new InsufficientStockException("There is not enough stock of the product.");
			}
		}
	}

	/**
	 * Receive an order and an id of a existing product, plus a positive quantity 
	 * Eliminate the indicated amount of the product
	 * @param ord 			the order which stock the different products and the quantities.
	 * @param productId		the id of the product which will be add to the order.
	 * @param quantity		the quantity of the product.
	 * @throws InsufficientStockException			If the quantity to remove is bigger than the quantity which is stock in the order
	 * @throws ProductNotContainedInOrderException	If the product isn't in the basket
	 */
	public void removeProductFromOrder(OrderImpl ord, int productId, int quantity)throws InsufficientStockException, ProductNotContainedInOrderException
	{
		int quantbasket = ord.checkProductQuantity(productId);
		if(ord.containsProduct(productId))
		{
			if(quantity > 0 && quantity <= quantbasket)
			{
				ord.removeProduct(productId, quantity);
			}
			else
			{
				throw new InsufficientStockException("Can't remove that amount of product.");
			}
		}	
		else
		{
			throw new ProductNotContainedInOrderException("This product is not in your basket.");
		}
		
	}
	
	/* ---------------------------------ORDER_STATUS----------------------------------*/
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord	the order which status change
	 */
	public void OrderStatus_InKitchen(OrderImpl ord)throws UnreachableStatusException
	{
		//There must be products in the basket and the order be in the open state
		if(!ord.getProducts().isEmpty() && ord.getStatus() == OrderStatus.OPEN)
		{
			ord.setStatus(OrderStatus.IN_KITCHEN);
		}
		else
		{
			throw new UnreachableStatusException("The order has no products to send to the kitchen.");
		}
	}
	
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord	the order which status change
	 */
	public void OrderStatus_Delivered(OrderImpl ord)throws UnreachableStatusException
	{
		//The state must be in the kitchen to be delivered
		if(ord.getStatus() == OrderStatus.IN_KITCHEN)
		{
			ord.setStatus(OrderStatus.DELIVERED);
		}
		else
		{
			throw new UnreachableStatusException("The order cannot be entered if it has not been in the kitchen.");
		}
	}
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord	the order which status change
	 */
	public void OrderStatus_Payed(OrderImpl ord)throws UnreachableStatusException
	{
		//The state must be delivered or in the kitchen in order to be paid
		if(ord.getStatus() == OrderStatus.IN_KITCHEN || ord.getStatus() == OrderStatus.DELIVERED)
		{
			ord.setStatus(OrderStatus.PAYED);
		}
		else
		{
			throw new UnreachableStatusException("The order cannot be charged because it is not yet in the kitchen or delivered.");
		}
		
	}
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord	the order which status change
	 */
	public void OrderStatus_Finished(OrderImpl ord)throws UnreachableStatusException
	{
		// The state must be charged in order to be finalized
		if(ord.getStatus() == OrderStatus.PAYED)
		{
			ord.setStatus(OrderStatus.FINISHED);
		}
		else
		{
			throw new UnreachableStatusException("The order cannot be finalized because it has not been charged.");
		}
		
	}
	

	/**
	 * Receive a date
	 * Return the total of all orders for the date entered.
	 * @param coffe		Cafeteria which stock the different daily register according to the date.
	 * @param date		Date of the Daily Register which get the profit
	 * @return 			total profit of the date received
	 */
	public BigDecimal getDailyRegister(Cafeteria coffe, LocalDate date)
	{
		BigDecimal dailyRegister = BigDecimal.ZERO;
		
		//We go through the order history of the establishment
		for(Order ord: coffe.getOrders())
		{
			if(ord.getDate() == date)
			{
				dailyRegister = dailyRegister.add(ord.totalCost());
			}
		}
		return dailyRegister;
	}
	
	
	
}
