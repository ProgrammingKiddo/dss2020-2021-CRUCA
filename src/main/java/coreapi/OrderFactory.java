package coreapi;

public class OrderFactory 
{
	
	//Save the order number, which is correlative and cannot be repeated
	//It starts at 0 because the first registered order will have 0 as its identification number
	private static int orderCount = 0;
	
	public ImplOrder createOrder()
	{
		return ImplOrder(orderCount++);//We are increasing the number so that they are correlative
		
	}	
	
}
