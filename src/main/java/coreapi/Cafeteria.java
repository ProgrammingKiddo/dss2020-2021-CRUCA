package coreapi;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Cafeteria {

	public LinkedHashMap<Product, Integer> productStock;
	public ArrayList<Order> orderHistory;
	public ArrayList<Order> dailyOrders;
	
	Cafeteria()
	{
		productStock = new LinkedHashMap<Product, Integer>();
	}
}
