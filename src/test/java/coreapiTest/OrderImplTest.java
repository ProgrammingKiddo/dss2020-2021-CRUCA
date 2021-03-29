package coreapiTest;

import coreapi.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderImplTest {

	private OrderImpl myOrder;
	
	private Product product1 = ProductCatalog.Instance().getProduct(0);
	private Product product2 = ProductCatalog.Instance().getProduct(1);
	
	@Before
	public void setUp()
	{
		myOrder = (OrderImpl) OrderFactory.createOrder();
	}
	
	@After
	public void tearDown()
	{
		myOrder = null;
	}
	
	@Test
	public void UniqueIdCheck()
	{
		Assert.assertEquals(OrderFactory.getOrderCount()-1, myOrder.getId());
	}
	
	@Test
	public void GetStatusCheck()
	{
		Assert.assertEquals(OrderStatus.OPEN, myOrder.getStatus());
	}
	
	@Test
	public void SetStatusCheck()
	{
		myOrder.setStatus(OrderStatus.FINISHED);
		Assert.assertEquals("Order hasn't set status properly.", OrderStatus.FINISHED, myOrder.getStatus());
	}
	
	@Test
	public void ContainsProductCheck()
	{
		Assert.assertFalse(myOrder.containsProduct(product1.getId()));
		myOrder.addProduct(product1.getId());
		Assert.assertTrue(myOrder.containsProduct(product1.getId()));
	}
	
	@Test
	public void ProductQuantityCheck()
	{
		Assert.assertEquals(0,  myOrder.checkProductQuantity(product1.getId()));
		myOrder.addProduct(product1.getId());
		Assert.assertEquals(1,  myOrder.checkProductQuantity(product1.getId()));
	}
	
	@Test
	public void GetProductsCheck()
	{
		myOrder.addProduct(product1.getId());
		myOrder.addProduct(product2.getId());
		List<Product> tempList = myOrder.getProducts();
		Assert.assertEquals(product1.getId(), tempList.get(tempList.indexOf(product1)).getId());
		Assert.assertEquals(product2.getId(), tempList.get(tempList.indexOf(product2)).getId());
	}
	
	@Test
	public void GetEmptyProductsCheck()
	{
		Assert.assertTrue(myOrder.getProducts().isEmpty());
	}
	
	@Test
	public void GetBasketCheck()
	{
		myOrder.addProduct(product1.getId(), 2);
		myOrder.addProduct(product2.getId(), 4);
		Map<Product, Integer> tempMap = myOrder.getBasket();
		Assert.assertEquals(2, tempMap.get(product1).intValue());
		Assert.assertEquals(4, tempMap.get(product2).intValue());
	}
	
	@Test
	public void GetEmptyBasketCheck()
	{
		Assert.assertTrue(myOrder.getBasket().isEmpty());
	}

	
	@Test
	public void AddSingleProductCheck()
	{
		//Assert.assertFalse(myOrder.containsProduct(Product1.getId()));
		myOrder.addProduct(product1.getId());
		Assert.assertTrue(myOrder.containsProduct(product1.getId()));
	}
	
	@Test
	public void AddProductQuantityCheck()
	{
		//Assert.assertEquals(0, myOrder.checkProductQuantity(Product1.getId()));
		myOrder.addProduct(product1.getId(), 4);
		Assert.assertEquals(4,  myOrder.checkProductQuantity(product1.getId()));
	}
	
	@Test
	public void AddNonPositiveProductQuantityCheck()
	{
		//Assert.assertEquals(0, myOrder.checkProductQuantity(Product1.getId()));
		myOrder.addProduct(product1.getId(), -4);
		Assert.assertFalse(myOrder.containsProduct(product1.getId()));
		myOrder.addProduct(product1.getId(), 0);
		Assert.assertFalse(myOrder.containsProduct(product1.getId()));
	}
	
	@Test
	public void RemoveAllProductCheck()
	{
		myOrder.addProduct(product1.getId());
		myOrder.removeProduct(product1.getId());
		Assert.assertFalse(myOrder.containsProduct(product1.getId()));
		
		myOrder.addProduct(product1.getId(), 4);
		myOrder.removeProduct(product1.getId(), 5);
		Assert.assertFalse(myOrder.containsProduct(product1.getId()));
	}
	
	@Test
	public void RemoveProductQuantityCheck()
	{
		myOrder.addProduct(product1.getId(), 4);
		myOrder.removeProduct(product1.getId(), 1);
		Assert.assertEquals(3, myOrder.checkProductQuantity(product1.getId()));
	}
	
	@Test
	public void RemoveNonPositiveProductQuantityCheck()
	{
		myOrder.addProduct(product1.getId(), 4);
		myOrder.removeProduct(product1.getId(), -4);
		Assert.assertEquals(4, myOrder.checkProductQuantity(product1.getId()));
		myOrder.removeProduct(product1.getId(), 0);
		Assert.assertEquals(4, myOrder.checkProductQuantity(product1.getId()));
	}
	
	@Test
	public void TotalCostEmptyCheck()
	{
		Assert.assertEquals(0.0, myOrder.totalCost(), 0.0);
	}
	
	@Test
	public void TotalCostCheck()
	{
		myOrder.addProduct(product1.getId(), 2);
		myOrder.addProduct(product2.getId(), 4);
		float calculatedCost = (product1.getPrice() * 2) + (product2.getPrice() * 4);
		Assert.assertEquals(calculatedCost, myOrder.totalCost(), 0.0);
	}
	
}
