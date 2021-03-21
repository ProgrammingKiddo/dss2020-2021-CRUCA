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
	
	@Before
	protected void setUp()
	{
		menu1 = new Menu(1,"Oro");
		menu2 = new Menu(2,"Plata");
		menu3 = new Menu(3,"Bronce");
	}
	
	@After
	protected void tearDown()
	{
		menu1 = null;
		menu2 = null;
		menu3 = null;
	}
	
	@Test
	public void IdCheckMenu()
	{
		assertEquals("DifferentIdAtMenu1",1,menu1.getId());
		assertEquals("DifferentIdAtMenu2",2,menu2.getId());
		assertEquals("DifferentIdAtMenu3",3,menu3.getId());
	}
}