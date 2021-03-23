package coreapi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MenuTest
{
	private Menu menu1;
	private Menu menu2;
	private Menu menu3;
	private Product p1;
	private Product p2;
	private Product p3;
	
	@Before
	protected void setUp()
	{
		menu1 = new Menu(1,"Oro");
		menu2 = new Menu(2,"Plata");
		menu3 = new Menu(3,"Bronce");
		p1 = ProductCatalog.getProduct(0);
		p2 = ProductCatalog.getProduct(1);
		p3 = ProductCatalog.getProduct(2);
	}
	
	@After
	protected void tearDown()
	{
		menu1 = null;
		menu2 = null;
		menu3 = null;
	}
	
	@Test
	public void addProductToMenuCheck()
	{
		menu1.addProductToMenu(p1,1);
		menu1.addProductToMenu(p2,2);
		menu1.addProductToMenu(p3,3);
		Set<Product> containedProducts = menu1.productsMenu.keySet();
		Iterator<Product> prodIterator = containedProducts.iterator();
		int Comp = 0;
		
		while (prodIterator.hasNext())
		{
			Assert.assertEquals("AddProductToMenuError",prodIterator.next().getId(),Comp);
			Assert.assertEquals("AddProductToMenuError",menu1.productsMenu.get(prodIterator.next()),Comp+1);
		}
	}
	
	@Test
	public void IdCheckMenu()
	{
		Assert.assertEquals("DifferentIdAtMenu1",1,menu1.getId());
		Assert.assertEquals("DifferentIdAtMenu2",2,menu2.getId());
		Assert.assertEquals("DifferentIdAtMenu3",3,menu3.getId());
	}
	
	@Test
	public void NameCheckMenu()
	{
		Assert.assertEquals("DifferentNameAtMenu1","Oro",menu1.getName());
		Assert.assertEquals("DifferentNameAtMenu2","Plata",menu2.getName());
		Assert.assertEquals("DifferentNameAtMenu3","Bronce",menu3.getName());
	}
	
	@Test
	public void PriceCheckMenu()
	{
		Assert.assertEquals("DifferentPriceAtMenu1",menu1.getName());
		Assert.assertEquals("DifferentPriceAtMenu2",menu2.getName());
		Assert.assertEquals("DifferentPriceAtMenu3",menu3.getName());
	}
	
	@Test
	public void GetProductsInMenuCheck()
	{
		menu2.addProductToMenu(p1,1);
		menu2.addProductToMenu(p2,2);
		menu2.addProductToMenu(p3,3);
		int Comp = 0;
		List<Product> MenuProducts = menu2.getProductsInMenu();
		
		for(Product p : MenuProducts)
		{
			Assert.assertEquals("AddProductToMenuError",p.getId(),Comp);
		}
	}
}