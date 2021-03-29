package coreapiTest;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import coreapi.Menu;
import coreapi.Product;
import coreapi.ProductCatalog;


public class MenuTest
{
	private Menu myMenu;
	private Product product1 = ProductCatalog.getProduct(0);
	private Product product2 = ProductCatalog.getProduct(1);
	
	@Before
	public void setUp()
	{
		myMenu = new Menu(1, "Oro");
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
		Assert.assertEquals(4, myMenu.getProductQuantity(product2.getId()));
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
		float expectedPrice = (product1.getPrice() * 2) + (product2.getPrice() * 4);
		Assert.assertEquals(expectedPrice, myMenu.getPrice(), 0.0);
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