package coreapi;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Cafeteria 
{

	public LinkedHashMap<Product, Integer> productStock;
	public ArrayList<Order> orderHistory;
	public ArrayList<Order> dailyOrders;
	public OrderFactory ordFact;
	
	public Cafeteria()
	{
		productStock = new LinkedHashMap<Product, Integer>();
		ordFact = new OrderFactory();
		orderHistory = new ArrayList<Order>();
	}
	
	public void ProductRegister(Product prod, int q)
	{
		productStock.put(prod,q);
	}
	
}
