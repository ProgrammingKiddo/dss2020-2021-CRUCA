package coreapiTest;

import coreapi.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OrderServiceTest 
{

	private Cafeteria coffe;
	private OrderImpl myOrder;
	private OrderService ordSer;
	private LocalDate date;
	private Product product0 = new ProductImpl(0, new BigDecimal(1.2), "Patatas fritas", "Comida");
	private Product product1 = new ProductImpl(1, new BigDecimal(1.7), "Bacon-queso-huevo","Menu");
	private Product product2 = new ProductImpl(2, new BigDecimal(0.9), "Café con leche","Bebida");

	
	@Before
	public void setUp()
	{
		coffe = new Cafeteria(0, "Cafeteria de Santa Fe","santafe@gmail.com");
		date = LocalDate.now();
		myOrder = (OrderImpl) OrderFactory.createOrder(coffe);
		//ordSer = new OrderService();
		coffe.registerOrder(myOrder);
		
		// We introduce the products to the cafeteria with a certain stock
		coffe.registerProduct(product0, 8);
		coffe.registerProduct(product1, 35);
		coffe.registerProduct(product2, 30);
	}
	
	@After
	public void tearDown()
	{
		coffe = null;
		myOrder = null;
	}
	
	/*---------------------------ORDER_PRODUCTS_CHECK------------------------------------------*/
	
	/*_____________________________ADD_PRODUCT_TO_ORDER_CHECK__________________________________*/
	
	@Test
	/*
	 * Check that the product has been added to the order correctly in case the product is not
	 * in the basket and there is enough stock.
	 */
	public void addProductToOrder_Correctly()
	{
		try
		{
			ordSer.addProductToOrder(coffe, myOrder, product1, 30);
		}
		catch(Exception e) 
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals(30, myOrder.checkProductQuantity(product1));
	}
	
	@Test
	/*
	 * Check that the product is not added to the order if there is not enough stock.
	 */
	public void addProductToOrder_NoStock()
	{
		Assert.assertThrows(InsufficientStockException.class, () -> {
			ordSer.addProductToOrder(coffe, myOrder, product0, 10);
		});
		Assert.assertFalse(myOrder.containsProduct(product0));
	}

	
	/*_____________________________REMOVE_PRODUCT_FROM_ORDER_CHECK__________________________________*/
	
	@Test
	/*
	 * Check that the indicated amount of product is eliminated since the product is in the basket 
	 * with an amount equal to or greater than that indicated.
	 */
	public void removeProductFromOrder_Correctly()
	{
		try
		{
			ordSer.addProductToOrder(coffe, myOrder, product2, 15);
			ordSer.removeProductFromOrder(coffe, myOrder, product2, 10);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals("The incorrect amount of product was removed from the order.", 5,
				myOrder.checkProductQuantity(product2));
	}
	
	@Test
	/*
	 *Check that the amount of product indicated is not eliminated since the product is in the basket
	 *with a smaller quantity than indicated.
	 */
	public void removeProductFromOrder_NotEnoughQuantity()
	{
		try {
			ordSer.addProductToOrder(coffe, myOrder, product2, 1);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		Assert.assertThrows(InsufficientStockException.class, () -> {
			ordSer.removeProductFromOrder(coffe, myOrder, product2, 100);
		});
		Assert.assertEquals(1, myOrder.checkProductQuantity(product2));
	}
	
	@Test
	/*
	 *Check that the indicated amount of product is not eliminated, 
	 *since the product is not in the basket
	 */
	public void removeProductFromOrder_NotInBasket()
	{
		Assert.assertThrows(ProductNotContainedInOrderException.class, () -> {
			ordSer.removeProductFromOrder(coffe, myOrder, product0, 4);
		});
		
	}
	
	/* ---------------------------------ORDER_STATUS_CHECK----------------------------------*/
	
	@Test
	/*
	 * Check that the Order Status can't be changed to the one indicated.
	 */
	public void checkInvalidSetOrderStatus_InKitchen()
	{
		Assert.assertThrows(UnreachableStatusException.class, () -> {
			ordSer.OrderStatus_InKitchen(myOrder);
		});
	}
	
	@Test
	/*
	 * Check that the Order Status can't be changed to the one indicated.
	 */
	public void checkInvalidSetOrderStatus_Delivered()
	{
		Assert.assertThrows(UnreachableStatusException.class, () -> {
			ordSer.OrderStatus_Delivered(myOrder);
		});
	}
	
	@Test
	/*
	 * Check that the Order Status can't be changed to the one indicated.
	 */
	public void checkInvalidSetOrderStatus_Payed()
	{
		Assert.assertThrows(UnreachableStatusException.class, () -> {
			ordSer.OrderStatus_Payed(myOrder);
		});
	}
	
	@Test
	/*
	 * Check that the Order Status can't be changed to the one indicated.
	 */
	public void checkInvalidSetOrderStatus_Finished()
	{
		Assert.assertThrows(UnreachableStatusException.class, () -> {
			ordSer.OrderStatus_Finished(myOrder);
		});
	}
	
	
	/*-------------------------------DAILY_REGISTER_CHECK-------------------------------*/
	
	@Test
	/*
	 *Check that the total of the orders for the date entered is correct.
	 */
	public void testGetDailyRegister()
	{
		OrderImpl auxOrder1 = (OrderImpl) OrderFactory.createOrder(coffe);
		OrderImpl auxOrder2 = (OrderImpl) OrderFactory.createOrder(coffe);
		coffe.registerOrder(auxOrder1);
		coffe.registerOrder(auxOrder2);
		
		try {
			ordSer.addProductToOrder(coffe, myOrder, product0, 3);
			ordSer.addProductToOrder(coffe, myOrder, product1, 1);
			ordSer.addProductToOrder(coffe, auxOrder1, product1, 4);
			ordSer.addProductToOrder(coffe, auxOrder2, product2, 8);
			

			BigDecimal expectedRegister = BigDecimal.ZERO;
			expectedRegister = expectedRegister.add(myOrder.totalCost()).add(auxOrder1.totalCost()).add(auxOrder2.totalCost());

			Assert.assertEquals(0, expectedRegister.compareTo(ordSer.getTotalDailyRegister(coffe, date)));
		
		}
		catch (Exception e) { }
		
	}
}
