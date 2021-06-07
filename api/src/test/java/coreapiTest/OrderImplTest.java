/**
 * @author Borja
 * @version 0.2
 */
package coreapiTest;

import coreapi.*;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderImplTest {

	private OrderImpl myOrder;
	private Cafeteria cafet = new Cafeteria(0, "Santa Fe", "santafe@gmail.com");
	private Product product1 = new ProductImpl(0, new BigDecimal(1.2), "Patatas fritas", "Comida");
	private Product product2 = new ProductImpl(1, new BigDecimal(1.7), "Bacon-queso-huevo","Menu");
	
	@Before
	public void setUp()
	{
		myOrder = (OrderImpl) OrderFactory.createOrder(cafet);
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
		Assert.assertFalse(myOrder.containsProduct(product1));
		myOrder.addProduct(product1);
		Assert.assertTrue(myOrder.containsProduct(product1));
	}
	
	@Test
	public void ProductQuantityCheck()
	{
		Assert.assertEquals(0,  myOrder.checkProductQuantity(product1));
		myOrder.addProduct(product1);
		Assert.assertEquals(1,  myOrder.checkProductQuantity(product1));
	}
	
	@Test
	public void GetProductsCheck()
	{
		myOrder.addProduct(product1);
		myOrder.addProduct(product2);
		List<Product> tempList = myOrder.getProducts();
		Assert.assertEquals(product1, tempList.get(tempList.indexOf(product1)));
		Assert.assertEquals(product2, tempList.get(tempList.indexOf(product2)));
	}
	
	@Test
	public void GetEmptyProductsCheck()
	{
		Assert.assertTrue(myOrder.getProducts().isEmpty());
	}
	
	@Test
	public void GetBasketCheck()
	{
		myOrder.addProduct(product1, 2);
		myOrder.addProduct(product2, 4);
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
		myOrder.addProduct(product1);
		Assert.assertTrue(myOrder.containsProduct(product1));
	}
	
	@Test
	public void AddProductQuantityCheck()
	{
		myOrder.addProduct(product1, 4);
		Assert.assertEquals(4,  myOrder.checkProductQuantity(product1));
	}
	
	@Test
	public void AddNonPositiveProductQuantityCheck()
	{
		myOrder.addProduct(product1, -4);
		Assert.assertFalse(myOrder.containsProduct(product1));
		myOrder.addProduct(product1, 0);
		Assert.assertFalse(myOrder.containsProduct(product1));
	}
	
	@Test
	public void RemoveAllProductCheck()
	{
		myOrder.addProduct(product1);
		myOrder.removeProduct(product1);
		Assert.assertFalse(myOrder.containsProduct(product1));
		
		myOrder.addProduct(product1, 4);
		myOrder.removeProduct(product1, 5);
		Assert.assertFalse(myOrder.containsProduct(product1));
	}
	
	@Test
	public void RemoveProductQuantityCheck()
	{
		myOrder.addProduct(product1, 4);
		myOrder.removeProduct(product1, 1);
		Assert.assertEquals(3, myOrder.checkProductQuantity(product1));
	}
	
	@Test
	public void RemoveNonPositiveProductQuantityCheck()
	{
		myOrder.addProduct(product1, 4);
		myOrder.removeProduct(product1, -4);
		Assert.assertEquals(4, myOrder.checkProductQuantity(product1));
		myOrder.removeProduct(product1, 0);
		Assert.assertEquals(4, myOrder.checkProductQuantity(product1));
	}
	
	@Test
	public void TotalCostEmptyCheck()
	{
		Assert.assertEquals(0, myOrder.totalCost().compareTo(BigDecimal.ZERO));
	}
	
	@Test
	public void TotalCostCheck()
	{
		myOrder.addProduct(product1, 2);
		myOrder.addProduct(product2, 4);
		
		BigDecimal product1Cost = product1.getPrice();
		BigDecimal product2Cost = product2.getPrice();
		product1Cost = product1Cost.multiply(new BigDecimal(2));
		product2Cost = product2Cost.multiply(new BigDecimal(4));
		
		BigDecimal expectedCost = product1Cost.add(product2Cost);
		Assert.assertEquals(0, expectedCost.compareTo(myOrder.totalCost()));
	}
	
}
