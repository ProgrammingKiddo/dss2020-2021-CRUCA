package coreapiTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			ProductImplTest.class,
			ProductTest.class,
			OrderImplTest.class,
			MenuTest.class,
			OrderFactoryTest.class,
			OrderServiceTest.class
		})

public class CoffeeShopTestSuite
{
	//Empty
}