package coreapiTest;

import coreapi.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OrderServiceTest 
{

	private Cafeteria coffe;
	private OrderImpl myOrder;
	private OrderService ordSer;
	private Date date;
	private Product product1 = ProductCatalog.Instance().getProduct(0);
	private Product product2 = ProductCatalog.Instance().getProduct(1);
	private Product product3 = ProductCatalog.Instance().getProduct(2);

	
	@Before
	public void setUp()
	{
		coffe = new Cafeteria();
		date = new Date(System.currentTimeMillis());
		myOrder = (OrderImpl) OrderFactory.createOrder(date);
		ordSer = new OrderService();
		coffe.registerOrder(myOrder);
		
		// We introduce the products to the cafeteria with a certain stock
		coffe.registerProductQuantity(product1, 8);
		coffe.registerProductQuantity(product2, 35);
		coffe.registerProductQuantity(product3, 30);
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
	public void addProductToOrder_Correctly_OrderService()
	{
		try
		{
			ordSer.addProductToOrder(coffe, myOrder, 1, 30);
		}
		catch(Exception e) 
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals(30, myOrder.checkProductQuantity(1));
	}
	
	@Test
	/*
	 * Check that the product is not added to the order if there is not enough stock.
	 */
	public void addProductToOrder_NoStock_OrderService()
	{
		Assert.assertThrows(InsufficientStockException.class, () -> {
			ordSer.addProductToOrder(coffe, myOrder, 0, 10);
		});
		Assert.assertFalse(myOrder.containsProduct(0));
	}
	
	@Test
	/*
	 * Check that the product is not added to the order if it is already in the basket.
	 */
	public void addProductToOrder_ProductInBasket_OrderService()
	{
		try {
			ordSer.addProductToOrder(coffe, myOrder, 1, 1);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		Assert.assertThrows(ProductAlreadyInOrderException.class, () -> {
			ordSer.addProductToOrder(coffe, myOrder, 1, 1);
		});	
	}
	
	/*_____________________________MODIFY_PRODUCT_QUANTITY_CHECK__________________________________*/
	
	@Test
	/*
	 *Check that the quantity of the product is modified if it is in the basket 
	 *and there is enough stock.
	 */
	public void modifyProductQuantity_Correctly_OrderService()
	{
		try
		{
			ordSer.addProductToOrder(coffe, myOrder, 2, 1);
			ordSer.modifyProductQuantity(coffe, myOrder, 2, 10);
		}
		catch(Exception e) 
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals(11, myOrder.checkProductQuantity(2));
	}
	
	@Test
	/*
	 *Check that the product quantity is not modified if there is not enough stock.
	 */
	public void modifyProductQuantity_NoStock_OrderService()
	{
		try {
			ordSer.addProductToOrder(coffe, myOrder, 2, 1);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		Assert.assertThrows(InsufficientStockException.class, () -> {
			ordSer.modifyProductQuantity(coffe, myOrder, 2, 50);
		});
		Assert.assertEquals(1, myOrder.checkProductQuantity(2));;
	}
		
	@Test
	/*
	 *Check that the quantity of the product is not modified if it is not in the basket.
	 */
	public void modifyProductQuantity_NotInBasket_OrderService()
	{
		Assert.assertThrows(ProductNotContainedInOrderException.class, () -> {
			ordSer.modifyProductQuantity(coffe, myOrder, 0, 4);
		});
	}
	
	/*_____________________________REMOVE_PRODUCT_FROM_ORDER_CHECK__________________________________*/
	
	@Test
	/*
	 * Check that the indicated amount of product is eliminated since the product is in the basket 
	 * with an amount equal to or greater than that indicated.
	 */
	public void removeProductFromOrder_Correctly_OrderService()
	{
		try
		{
			ordSer.addProductToOrder(coffe, myOrder, 2, 15);
			ordSer.removeProductFromOrder(myOrder, 2, 10);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals("The incorrect amount of product was removed from the order.", 5,
				myOrder.checkProductQuantity(2));
	}
	
	@Test
	/*
	 *Check that the amount of product indicated is not eliminated since the product is in the basket
	 *with a smaller quantity than indicated.
	 */
	public void removeProductFromOrder_NotEnoughQuantity_OrderService()
	{
		try {
			ordSer.addProductToOrder(coffe, myOrder, 2, 1);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		Assert.assertThrows(InsufficientStockException.class, () -> {
			ordSer.removeProductFromOrder(myOrder, 2, 100);
		});
		Assert.assertEquals(1, myOrder.checkProductQuantity(2));
	}
	
	@Test
	/*
	 *Check that the indicated amount of product is not eliminated, 
	 *since the product is not in the basket
	 */
	public void removeProductFromOrder_NotInBasket_OrderService()
	{
		Assert.assertThrows(ProductNotContainedInOrderException.class, () -> {
			ordSer.removeProductFromOrder(myOrder, 0, 4);
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
	public void DailyRegisterCheck_OrderService()
	{
		OrderImpl auxOrder1 = (OrderImpl) OrderFactory.createOrder(date);
		OrderImpl auxOrder2 = (OrderImpl) OrderFactory.createOrder(date);
		coffe.registerOrder(auxOrder1);
		coffe.registerOrder(auxOrder2);
		
		try {
			ordSer.addProductToOrder(coffe, myOrder, 0, 3);
			ordSer.addProductToOrder(coffe, myOrder, 1, 1);
			ordSer.addProductToOrder(coffe, auxOrder1, 1, 4);
			ordSer.addProductToOrder(coffe, auxOrder2, 2, 8);			
		}
		catch (Exception e) { }
		
		BigDecimal expectedRegister = BigDecimal.ZERO;
		expectedRegister = expectedRegister.add(myOrder.totalCost()).add(auxOrder1.totalCost()).add(auxOrder2.totalCost());

		Assert.assertEquals(0, expectedRegister.compareTo(ordSer.getDailyRegister(coffe, date)));
	
	}
}
