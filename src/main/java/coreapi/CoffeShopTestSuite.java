package coreapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			ProductImplTest.class,
			ProductTest.class,
			MenuTest.class,
			OrderServiceTest.class,
			OrderFactoryTest.class
		})

public class CoffeShopTestSuite
{
	//Empty
}