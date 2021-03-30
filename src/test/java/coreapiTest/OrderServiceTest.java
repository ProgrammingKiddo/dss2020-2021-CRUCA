package coreapiTest;

import coreapi.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert.*;

public class OrderServiceTest 
{

	private Cafeteria coffe;
	private OrderImpl ord1;
	private OrderImpl ord2;
	private OrderImpl ord3;
	private OrderService ordSer;
	private Date date;
	private Product Product1;
	private Product Product2;
	private Product Product3;

	
	@Before
	public void setUp()
	{
		coffe = new Cafeteria();
		date = new Date(System.currentTimeMillis());
		ord1 = (OrderImpl) OrderFactory.createOrder(date);
		ord2 = (OrderImpl) OrderFactory.createOrder(date);
		ord3 = (OrderImpl) OrderFactory.createOrder(date);
		ordSer = new OrderService();
		
		// We introduce products from the catalog to the cafeteria with a certain stock
		Product1 = ProductCatalog.Instance().getProduct(0);
		Product2 = ProductCatalog.Instance().getProduct(1);
		Product3 = ProductCatalog.Instance().getProduct(2);
		coffe.ProductRegister(Product1, 10);
		coffe.ProductRegister(Product2, 8);
		coffe.ProductRegister(Product3, 30);
	}
	
	@After
	public void tearDown()
	{
		coffe = null;
		ord1 = null;
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
			ordSer.addProductToOrder(coffe,ord1,2,30);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),null);
		}
	}
	
	@Test
	/*
	 * Check that the product is not added to the order if there is not enough stock.
	 */
	public void addProductToOrder_NoStock_OrderService()
	{
		try
		{
		
			ordSer.addProductToOrder(coffe,ord1,1,10);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"There is not enough stock of the product.");
		}
	}
	
	@Test
	/*
	 * Check that the product is not added to the order if it is already in the basket.
	 */
	public void addProductToOrder_ProductInBasket_OrderService()
	{
		try
		{
			ordSer.addProductToOrder(coffe,ord1,2,1);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"This product is already in your basket, you can modify the quantity of it if you wish.");
		}		
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
			ordSer.modifyProductQuantity(coffe, ord1, 2, 10);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),null);
		}
	}
	
	@Test
	/*
	 *Check that the product quantity is not modified if there is not enough stock.
	 */
	public void modifyProductQuantity_NoStock_OrderService()
	{
		try
		{
			ordSer.modifyProductQuantity(coffe, ord1, 2, 50);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"There is not enough stock of the product.");
		}
	}
		
	@Test
	/*
	 *Check that the quantity of the product is not modified if it is not in the basket.
	 */
	public void modifyProductQuantity_NotInBasket_OrderService()
	{
		try
		{
			ordSer.modifyProductQuantity(coffe, ord1, 0, 4);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"The product is not in your basket.");
		}	
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
			ordSer.removeProductFromOrder(ord1, 2, 10);
		}
		catch(Exception e)
		{
			Assert.assertEquals(e.getMessage(),null);
		}
		
	}
	
	@Test
	/*
	 *Check that the amount of product indicated is not eliminated since the product is in the basket
	 *with a smaller quantity than indicated.
	 */
	public void removeProductFromOrder_NotEnoughQuantity_OrderService()
	{
		try
		{
			ordSer.removeProductFromOrder(ord1, 2, 100);
		}
		catch(Exception e)
		{
			Assert.assertEquals(e.getMessage(),"Can't remove that amount of product.");
		}
		
	}
	
	@Test
	/*
	 *Check that the indicated amount of product is not eliminated, 
	 *since the product is not in the basket
	 */
	public void removeProductFromOrder_NotInBasket_OrderService()
	{
		try
		{
			ordSer.removeProductFromOrder(ord1, 0, 4);
		}
		catch(Exception e)
		{
			Assert.assertEquals(e.getMessage(), "This object is not in your basket.");
		}
		
	}
	
	/* ---------------------------------ORDER_STATUS_CHECK----------------------------------*/
	
	@Test
	/*
	 * Check that the Order Status has been changed to the one indicated.
	 */
	public void OrderStatus_InKitchenCheck_OrderService()
	{
		try
		{
			ordSer.OrderStatus_InKitchen(ord1);
		}
		catch(Exception e)
		{
			Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"IN_KITCHEN");	
		}
	}
	
	@Test
	/*
	 * Check that the Order Status has been changed to the one indicated.
	 */
	public void OrderStatus_DeliveredCheck_OrderService()
	{
		try
		{
			ordSer.OrderStatus_Delivered(ord1);
		}
		catch(Exception e)
		{
			Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"DELIVERED");
		}
	}
	
	@Test
	/*
	 * Check that the Order Status has been changed to the one indicated.
	 */
	public void OrderStatus_PayedCheck_OrderService()
	{
		try
		{
			ordSer.OrderStatus_Payed(ord1);
		}
		catch(Exception e)
		{
			Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"PAYED");
		}
	}
	
	@Test
	/*
	 *Check that the Order Status has been changed to the one indicated.
	 */
	public void OrderStatus_FinishedCheck_OrderService()
	{
		try
		{
			ordSer.OrderStatus_Finished(ord1);
		}
		catch(Exception e)
		{
			Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"FINISHED");
		}
	}
	
	
	/*-------------------------------DAILY_REGISTER_CHECK-------------------------------*/
	
	@Test
	/*
	 *Check that the total of the orders for the date entered is correct.
	 */
	public void DailyRegisterCheck_OrderService()
	{
		BigDecimal expectedRegister = BigDecimal.ZERO;
		expectedRegister = expectedRegister.add(ord1.totalCost()).add(ord2.totalCost()).add(ord3.totalCost());

		Assert.assertEquals(0, expectedRegister.compareTo(ordSer.getDailyRegister(coffe, date)));
	
	}
}
