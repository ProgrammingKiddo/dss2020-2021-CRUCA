package coreapi;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Cafeteria 
{

	public LinkedHashMap<Product, Integer> productStock;
	public ArrayList<Order> orderHistory;
	public ArrayList<Order> dailyOrders;
	public OrderFactory ordFact;
	
	Cafeteria()
	{
		productStock = new LinkedHashMap<Product, Integer>();
		ordFact = new OrderFactory();
	}
	
	public void ProductRegister(Product prod, int q)
	{
		productStock.put(prod,q);
	}
	
}
