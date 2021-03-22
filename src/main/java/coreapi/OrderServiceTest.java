package coreapi;

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
	private OrderService ordSer;
	private Date date;
	private Product Product1;
	private Product Product2;
	private Product Product3;

	
	@Before
	protected void setUp()
	{
		coffe = new Cafeteria();
		date = new Date(System.currentTimeMillis());
		ord1 = new OrderImpl(17, date);
		ordSer = new OrderService();
		Product1 = ProductCatalog.getProduct(0);
		Product2 = ProductCatalog.getProduct(1);
		Product3 = ProductCatalog.getProduct(2);
		coffe.ProductRegister(Product1, 10);
		coffe.ProductRegister(Product2, 8);
		coffe.ProductRegister(Product3, 30);
	}
	
	@After
	protected void tearDown()
	{
		coffe = null;
		ord1 = null;
	}
	
	/*---------------------------ORDER_PRODUCTS_CHECK------------------------------------------*/
	@Test
	public void addProductToOrderCheck_OrderService()
	{
		try
		{
			//Que no esté en la cesta y que haya stock suficiente
			ordSer.addProductToOrder(coffe,ord1,2,30);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),null);
		}
		
		try
		{
			//No hay stock
			ordSer.addProductToOrder(coffe,ord1,1,10);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"There is not enough stock of the product.");
		}
	
		try
		{
			//Ya se encuentra en la cesta
			ordSer.addProductToOrder(coffe,ord1,2,1);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"This product is already in your basket, you can modify the quantity of it if you wish.");
		}
		
			
	}
	
	@Test
	public void modifyProductQuantityCheck_OrderService()
	{
		try
		{
			//Está en la cesta y hay stock
			ordSer.modifyProductQuantity(coffe, ord1, 2, 10);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),null);
		}
		
		try
		{
			//Esta en la cesta y no hay stock
			ordSer.modifyProductQuantity(coffe, ord1, 2, 50);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"There is not enough stock of the product.");
		}
		
		try
		{
			//No esta en la lista
			ordSer.modifyProductQuantity(coffe, ord1, 0, 4);
		}
		catch(Exception e) 
		{
			Assert.assertEquals(e.getMessage(),"The product is not in your basket.");
		}
		
		
	}
	
	@Test
	public void removeProductFromOrderCheck_OrderService()
	{
		//Está en la cesta y hay la cantidad indicada a eliminar
		ordSer.removeProductFromOrder(ord1, 2, 10);
				
		//Esta en la cesta y no hay suficiente producto para eliminar
		ordSer.removeProductFromOrder(ord1, 2, 100);
				
		//No esta en la lista
		ordSer.removeProductFromOrder(ord1, 0, 4);
	}
	
	/* ---------------------------------ORDER_STATUS_CHECK----------------------------------*/
	@Test
	public void OrderStatus_InKitchenCheck_OrderService()
	{
		ordSer.OrderStatus_InKitchen(ord1);
		Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"IN_KITCHEN");	
	}
	
	@Test
	public void OrderStatus_DeliveredCheck_OrderService()
	{
		ordSer.OrderStatus_Delivered(ord1);
		Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"DELIVERED");
	}
	
	@Test
	public void OrderStatus_PayedCheck_OrderService()
	{
		ordSer.OrderStatus_Payed(ord1);
		Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"PAYED");
	}
	
	@Test
	public void OrderStatus_FinishedCheck_OrderService()
	{
		ordSer.OrderStatus_Finished(ord1);
		Assert.assertEquals("Incorrect Order Status",ord1.getStatus(),"FINISHED");
	}
	
	
	/*-------------------------------DAILY_REGISTER_CHECK-------------------------------*/
	
	@Test
	public void DailyRegisterCheck_OrderService()
	{
		
	}
}
