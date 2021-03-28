package coreapiTest;


import coreapi.*;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
	public static void main(String[] args)
	{
		ProductCatalog.createProducts();
		Result result = JUnitCore.runClasses(CoffeShopTestSuite.class);
		for(Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}
	}
}