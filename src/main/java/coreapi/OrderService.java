/**
 *  @author María
 *  @version 0.2
 */

package coreapi;

import java.math.BigDecimal;
import java.util.Date;

/*_________________________________________EXCEPTION_CLASSES______________________________ */

//Class that throws an exception when there is not enough stock of the product.
class InsufficientStock extends Exception
{
	public InsufficientStock(String msg)
	{
		super(msg);
	}
}

//Class that throws exceptions related to the products in the basket.
class ExistenceInTheBasket extends Exception
{
	public ExistenceInTheBasket(String msg)
	{
		super(msg);
	}
}

//Class that throws exceptions related to order status change.
class StatusException extends Exception
{
	public StatusException(String msg)
	{
		super(msg);
	}
}

/*_________________________________________CLASS_ORDERSERVICE______________________________ */
public class OrderService
{
	//Create a new object of OrderService
	public OrderService() {}

	
	/*---------------------------ORDER_PRODUCTS------------------------------------------*/
	
	/**
	 * Receive an order and an id of a existing product, plus a positive quantity 
	 * Add the product with the indicated quantity to the order
	 * @param coffe the Cafeteria which stock the different orders.
	 * @param ord the order which stock the different products and the quantities.
	 * @param productId the id of the product which will be add to the order.
	 * @param q the quantity of the product.
	 * @throws If the product is already in the basket
	 * @throws If there isn't enough stock of the product
	 */
	public void addProductToOrder(Cafeteria coffe, OrderImpl ord, int productId, int q)throws InsufficientStock, ExistenceInTheBasket
	{
		Product prod = ProductCatalog.Instance().getProduct(productId);
		if(ord.containsProduct(productId))
		{
			throw new ExistenceInTheBasket("This product is already in your basket, you can modify the quantity of it if you wish.");
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
					throw new InsufficientStock("There is not enough stock of the product.");
				}
			}
		}
		
	}
	
	/**
	 * Receive an order and an id of a existing product, plus a positive quantity 
	 * Modify the quantity of the product indicated in the order
	 * @param coffe the Cafeteria which stock the different orders.
	 * @param ord the order which stock the different products and the quantities.
	 * @param productId the id of the product which will be add to the order.
	 * @param q the quantity of the product.
	 * @throws If there isn't enough stock of the product
	 * @throws If the product isn't in the basket
	 */
	public void modifyProductQuantity(Cafeteria coffe, OrderImpl ord, int productId, int q)throws InsufficientStock, ExistenceInTheBasket
	{
		Product prod = ProductCatalog.Instance().getProduct(productId);
		
		if(coffe.productStock.containsKey(prod) && ord.containsProduct(productId))
		{
			if(q > 0 && coffe.productStock.get(prod).intValue() >= q)
			{
				ord.addProduct(productId,q);
			}
			else
			{
				throw new InsufficientStock("There is not enough stock of the product.");
			}
		}
		else
		{
			throw new ExistenceInTheBasket("The product is not in your basket.");
		}
	
	}

	/**
	 * Receive an order and an id of a existing product, plus a positive quantity 
	 * Eliminate the indicated amount of the product
	 * @param ord the order which stock the different products and the quantities.
	 * @param productId the id of the product which will be add to the order.
	 * @param q the quantity of the product.
	 * @throws If the quantity to remove is bigger than the quantity which is stock in the order
	 * @throws If the product isn't in the basket
	 */
	public void removeProductFromOrder(OrderImpl ord, int productId, int q)throws ExistenceInTheBasket, InsufficientStock
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
				throw new InsufficientStock("Can't remove that amount of product.");
			}
		}	
		else
		{
			throw new ExistenceInTheBasket("This object is not in your basket.");
		}
		
	}
	
	/* ---------------------------------ORDER_STATUS----------------------------------*/
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord the order which status change
	 */
	public void OrderStatus_InKitchen(OrderImpl ord)throws StatusException
	{
		//There must be products in the basket and the order be in the open state
		if(!ord.getProducts().isEmpty() && ord.getStatus() == OrderStatus.OPEN)
		{
			ord.setStatus(OrderStatus.IN_KITCHEN);
		}
		else
		{
			throw new StatusException("The order has no products to send to the kitchen.");
		}
	}
	
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord the order which status change
	 */
	public void OrderStatus_Delivered(OrderImpl ord)throws StatusException
	{
		//The state must be in the kitchen to be delivered
		if(ord.getStatus() == OrderStatus.IN_KITCHEN)
		{
			ord.setStatus(OrderStatus.DELIVERED);
		}
		else
		{
			throw new StatusException("The order cannot be entered if it has not been in the kitchen.");
		}
	}
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord the order which status change
	 */
	public void OrderStatus_Payed(OrderImpl ord)throws StatusException
	{
		//The state must be delivered or in the kitchen in order to be paid
		if(ord.getStatus() == OrderStatus.IN_KITCHEN || ord.getStatus() == OrderStatus.DELIVERED)
		{
			ord.setStatus(OrderStatus.PAYED);
		}
		else
		{
			throw new StatusException("The order cannot be charged because it is not yet in the kitchen or delivered.");
		}
		
	}
	/**
	 * Receive an order
	 * Assign the status to the order
	 * @param ord the order which status change
	 */
	public void OrderStatus_Finished(OrderImpl ord)throws StatusException
	{
		// The state must be charged in order to be finalized
		if(ord.getStatus() == OrderStatus.PAYED)
		{
			ord.setStatus(OrderStatus.FINISHED);
		}
		else
		{
			throw new StatusException("The order cannot be finalized because it has not been charged.");
		}
		
	}
	
	/*-------------------------------DAILY_REGISTER-------------------------------*/
	
	/*
	 * PRECONDITION: Receive a date
	 * POSTCONDITION: Return the total of all orders for the date entered.
	 */
	/**
	 * Receive a date
	 * Return the total of all orders for the date entered.
	 * @param coffe Cafeteria which stock the different daily register according to the date.
	 * @param date Date of the Daily Register which get the profit
	 * @return total profit of the date received
	 */
	public BigDecimal getDailyRegister(Cafeteria coffe, Date date)
	{
		BigDecimal dailyRegister = BigDecimal.ZERO;
		
		//We go through the order history of the establishment
		for(Order ord: coffe.orderHistory)
		{
			if(ord.getDate() == date)
			{
				dailyRegister = dailyRegister.add(ord.totalCost());
			}
		}
		return dailyRegister;
	}
	
	
	
}
