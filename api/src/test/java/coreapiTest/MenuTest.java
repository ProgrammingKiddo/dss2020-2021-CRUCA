package coreapiTest;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import coreapi.Menu;
import coreapi.Product;
import data.ProductData;
import coreapi.ProductImpl;

public class MenuTest
{
	private Menu myMenu;
	private Product product1 = new ProductImpl(0,new BigDecimal(1),"Botella de agua 1l","Bebidas");
	private Product product2 = new ProductImpl(1,new BigDecimal(1.75),"Bocadillo de Tortilla","Bocadillos");
	
	@Before
	public void setUp()
	{
		myMenu = new Menu(1, "Oro", "Menu");
	}
	
	@After
	public void tearDown()
	{
		myMenu = null;
	}
	
	@Test
	public void addProductToMenuCheck()
	{
		myMenu.addProductToMenu(product1, 1);
		myMenu.addProductToMenu(product2, 4);
		Assert.assertTrue(myMenu.getProductsInMenu().contains(product1));
		Assert.assertEquals(4, myMenu.getProductQuantity(product2));
	}
	
	@Test
	public void IdCheckMenu()
	{
		Assert.assertEquals("DifferentIdAtMenu1", 1, myMenu.getId());
	}
	
	@Test
	public void NameCheckMenu()
	{
		Assert.assertEquals("DifferentNameAtMenu1", "Oro", myMenu.getName());
	}
	
	@Test
	public void PriceCheckMenu()
	{
		myMenu.addProductToMenu(product1, 2);
		myMenu.addProductToMenu(product2, 4);

		BigDecimal product1Cost = product1.getPrice();
		BigDecimal product2Cost = product2.getPrice();
		product1Cost = product1Cost.multiply(new BigDecimal(2));
		product2Cost = product2Cost.multiply(new BigDecimal(4));
		
		BigDecimal expectedCost = product1Cost.add(product2Cost);
		Assert.assertEquals(0, expectedCost.compareTo(myMenu.getPrice()));
	}
	
	@Test
	public void GetProductsInMenuCheck()
	{
		myMenu.addProductToMenu(product1);
		myMenu.addProductToMenu(product2);
		List<Product> MenuProducts = myMenu.getProductsInMenu();
		
		Assert.assertTrue(MenuProducts.contains(product1));
		Assert.assertTrue(MenuProducts.contains(product2));
	}
}