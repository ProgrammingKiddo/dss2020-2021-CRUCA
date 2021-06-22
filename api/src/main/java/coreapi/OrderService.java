package coreapi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import data.*;

/**
 * This class acts as a service provider and interface for the client to work
 * with objects from the Order class.
 * 
 * Modifying and operating an object of the order class should always be done
 * through the methods provided here.
 *  @author Maria
 *  @author Borja
 *  @author Fran
 *  @version 0.2
 */

public class OrderService
{
	CafeteriaData cData;
	OrderData oData;
	ProductData pData;
	
	/**
	 * Create a new object of type OrderService with the information of a Cafeteria,
	 * order and specific products.
	 * @param cData		Data of a specific Cafeteria.
	 * @param oData		Data of a order.
	 * @param pData		Data of a specific products.
	 */
	public OrderService(CafeteriaData cData, OrderData oData, ProductData pData)
	{
		this.cData = cData;
		this.oData = oData;
		this.pData = pData;
	}

	
	/*---------------------------ORDER_PRODUCTS------------------------------------------*/
	
	/**
	 * Adds the indicated amount of product to the order passed as an argument.
	 * @param coffe			The Cafeteria related to the order.
	 * @param ord 			The order to add the product to.
	 * @param prod		 	The product to add to the order.
	 * @param quantity 		The quantity of product to add to the order.
	 * @throws InsufficientStockException	If there isn't enough stock of the product.
	 */
	public void addProductToOrder(Cafeteria coffe, Order ord, Product prod, int quantity)
			throws InsufficientStockException
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		
		if(coffe.getAvailableProducts().contains(prod))
		{
			if(quantity > 0 && coffe.getProductQuantity(prod) >= quantity)
			{
				coffe.removeStock(prod, quantity);
				orderDownCast.addProduct(prod, quantity);
				cData.saveCafeteria(coffe);
				oData.saveOrder(ord);
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
	 * @param prod			The product to remove from the order.
	 * @param quantity		The quantity of product to remove from the order.
	 * @param coffe			The coffee which restock the product.
	 * @throws InsufficientStockException			If the quantity to remove is bigger than the quantity which is stock in the order
	 * @throws ProductNotContainedInOrderException	If the product isn't in the basket
	 */
	public void removeProductFromOrder(Cafeteria coffe, Order ord, Product prod, int quantity)
			throws InsufficientStockException, ProductNotContainedInOrderException
	{
		OrderImpl orderDownCast = (OrderImpl) ord;

		int quantbasket = orderDownCast.checkProductQuantity(prod);
		if(orderDownCast.containsProduct(prod))
		{
			if(quantity > 0 && quantity <= quantbasket)
			{
				orderDownCast.removeProduct(prod, quantity);
				coffe.addProductStock(prod, quantity);
				cData.saveCafeteria(coffe);
				oData.saveOrder(ord);
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
	 * Sets the status of the order passed as parameter to <code>PAYED</code>
	 * if the order current status is either <code>OPEN</code>
	 * @param ord	The order to change the status of.
	 * @throws UnreachableStatusException	If the conditions to set the <code>PAYED</code> status aren't met.
	 */
	public void OrderStatus_Payed(Order ord)
			throws UnreachableStatusException
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		if(orderDownCast.getStatus() == OrderStatus.OPEN)
		{
			orderDownCast.setStatus(OrderStatus.PAYED);
			oData.saveOrder(ord);
		}
		else
		{
			throw new UnreachableStatusException("The order cannot be charged because it is closed.");
		}
		
	}
	/**
	 * Sets the status of the order passed as parameter to <code>FINISHED</code>
	 * if the order current status is <code>PAYED</code>.
	 * @param ord	The order to change the status of.
	 * @throws UnreachableStatusException	If the conditions to set the <code>FINISHED</code> status aren't met.
	 */
	public void OrderStatus_Finished(Order ord)
			throws UnreachableStatusException
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		// The state must be charged in order to be finalized
		if(orderDownCast.getStatus() == OrderStatus.PAYED)
		{
			orderDownCast.setStatus(OrderStatus.FINISHED);
			oData.saveOrder(ord);
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
	 * @throws InvalidDateException	If the date is greater than the system date.
	 */
	public BigDecimal getTotalDailyRegister(Cafeteria coffe, LocalDate date) throws InvalidDateException
	{
		BigDecimal dailyRegister = BigDecimal.ZERO;
		
		if(date.compareTo(LocalDate.now()) <= 0)
		{
			for(Order ord: coffe.getOrders())
			{
				if(ord.getDate().toLocalDate().equals(date))
				{
					dailyRegister = dailyRegister.add(ord.totalCost());
				}
			}
		}
		else
		{
			throw new InvalidDateException ("The date entered is greater than the current one, so there is no record for that date.");
		}
		return dailyRegister;
	}
	
	
	/**
	 * Returns the cash register from the indicated cafeteria on the designated day.
	 * @param coffee		The cafeteria from which to calculate the cash register.
	 * @param date		The day to which calculate the cash register.
	 * @return 			Returns the number of orders that have been on the specified date.
	 * @throws InvalidDateException	If the date is greater than the system date.
	 */
	public int getNumberOfDailyOrders(Cafeteria coffee, LocalDate date) throws InvalidDateException
	{
		int numberOfOrders = 0;
		if(date.compareTo(LocalDate.now()) <= 0)
		{
			for(Order ord: coffee.getOrders())
			{
				if(ord.getDate().toLocalDate().equals(date))
				{
					numberOfOrders++;
				}
			}
		}
		else
		{
			throw new InvalidDateException ("The date entered is greater than the current one, so there is no record for that date.");
		}
		return numberOfOrders;
	}
	
	/**
	 * Returns the validation code of the order.
	 * @param ord	The current order.
	 * @return		Returns the validation code of the concrete order.
	 */
	public String getValidationCode(Order ord)
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		return orderDownCast.getCode();
	}
	
	/**
	 * Returns true or false based on whether enough products are available from an order.
	 * @param ord	The current order.
	 * @param c		The current coffee.
	 * @return		Returns true if there is enough stock of products and false otherwise.
	 */
	public boolean getValidationStock(Order ord, Cafeteria c)
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		return orderDownCast.validationStock(c);
	}
	
	/**
	 * Returns the date for which the corresponding order has been scheduled.
	 * @param ord	The current order.
	 * @return		Returns the date and time of the scheduled order.
	 */
	public LocalDateTime getProgrammingDate(Order ord)
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		return orderDownCast.getProgrammingDate();
	}
	
	/**
	 * Set the validation code of this order to the one passed by parameter.
	 * @param s		The new validation code.
	 * @param ord	The current code.
	 */
	public void setCode(String s, Order ord)
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		orderDownCast.setCode(s);
	}
	
	/**
	 * Sets the programming date of this order to the one passed by parameter.
	 * @param ord	The current order.
	 * @param PD	The new programming date to set this order.
	 */
	public void setProgrammingDate(Order ord, LocalDateTime PD)
	{
		OrderImpl orderDownCast = (OrderImpl) ord;
		try
		{
			orderDownCast.setProgrammingDate(PD);
		}catch(InvalidDateException ex)
		{
			
		}
	}
}
