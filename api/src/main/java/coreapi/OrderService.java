package coreapi;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class acts as a service provider and interface for the client to work
 * with objects from the Order class.
 * 
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
	 * Adds the indicated amount of product to the order passed as an argument.
	 * @param cafet 		The Cafeteria related to the order.
	 * @param ord 			The order to add the product to.
	 * @param productId 	The id of the product to add to the order.
	 * @param quantity 		The quantity of product to add to the order.
	 * @throws InsufficientStockException	If there isn't enough stock of the product.
	 */
	public void addProductToOrder(Cafeteria cafet, OrderImpl ord, int productId, int quantity)
			throws InsufficientStockException
	{
		Product prod = ProductCatalog.Instance().getProduct(productId);
		if(cafet.getAvailableProducts().contains(prod))
		{
			if(quantity > 0 && cafet.getProductQuantity(prod) >= quantity)
			{
				cafet.removeStock(prod, quantity);
				ord.addProduct(productId, quantity);
			}
			else
			{
				throw new InsufficientStockException("There is not enough stock of the product.");
			}
		}
	}

	/**
	 * Removes the indicated amount of product from the order passed as an argument.
	 * @param ord 			The order to remove the product from.
	 * @param productId		The id of the product to remove from the order.
	 * @param quantity		The quantity of product to remove from the order.
	 * @throws InsufficientStockException			If the quantity to remove is bigger than the quantity which is stock in the order
	 * @throws ProductNotContainedInOrderException	If the product isn't in the basket
	 */
	public void removeProductFromOrder(OrderImpl ord, int productId, int quantity)
			throws InsufficientStockException, ProductNotContainedInOrderException
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
	 * Sets the status of the order passed as parameter to <code>IN_KITCHEN</code>
	 * if the order has at least one product and its current status is <code>OPEN</code>.
	 * @param ord	The order to change the status of.
	 * @throws UnreachableStatusException	If the conditions to set the <code>IN_KITCHEN</code> status aren't met.
	 */
	public void OrderStatus_InKitchen(OrderImpl ord)
			throws UnreachableStatusException
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
	 * Sets the status of the order passed as parameter to <code>DELIVERED</code>
	 * if the order current status is <code>IN_KITCHEN</code>.
	 * @param ord	The order to change the status of.
	 * @throws UnreachableStatusException	If the conditions to set the <code>DELIVERED</code> status aren't met.
	 */
	public void OrderStatus_Delivered(OrderImpl ord)
			throws UnreachableStatusException
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
	 * Sets the status of the order passed as parameter to <code>PAYED</code>
	 * if the order current status is either <code>IN_KITCHEN</code> or <code>DELIVERED</code>.
	 * @param ord	The order to change the status of.
	 * @throws UnreachableStatusException	If the conditions to set the <code>PAYED</code> status aren't met.
	 */
	public void OrderStatus_Payed(OrderImpl ord)
			throws UnreachableStatusException
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
	 * Sets the status of the order passed as parameter to <code>FINISHED</code>
	 * if the order current status is <code>PAYED</code>.
	 * @param ord	The order to change the status of.
	 * @throws UnreachableStatusException	If the conditions to set the <code>FINISHED</code> status aren't met.
	 */
	public void OrderStatus_Finished(OrderImpl ord)
			throws UnreachableStatusException
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
	 * Returns the number of orders register from the indicated cafeteria on the designated day.
	 * @param coffe		The cafeteria from which to calculate the cash register.
	 * @param date		The day to which calculate the cash register.
	 * @return 			Returns the combined cost of all orders made on the specified date.
	 */
	public BigDecimal getTotalDailyRegister(Cafeteria coffe, LocalDate date)
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
	
	
	/**
	 * Returns the cash register from the indicated cafeteria on the designated day.
	 * @param coffe		The cafeteria from which to calculate the cash register.
	 * @param date		The day to which calculate the cash register.
	 * @return 			Returns the number of orders that have been on the specified date.
	 */
	public BigDecimal getNumberOfOrdersDailyRegister(Cafeteria coffe, LocalDate date)
	{
		int n_orders = 0;
		
		//We go through the order history of the establishment
		for(Order ord: coffe.getOrders())
		{
			if(ord.getDate() == date)
			{
				dailyRegister++;
			}
		}
		return dailyRegister;
	}
	
}
