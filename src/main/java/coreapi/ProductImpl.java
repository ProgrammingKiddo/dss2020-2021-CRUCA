package coreapi;

public class ProductImpl implements Product {

	private int id;
	private float price;
	private int stock;
	private String name;
	
	
	public ProductImpl(int id, float price, String name)
	{
		this.id = id;
		this.price = price;
		this.name = name;
	}
	
	public int getId() { return id; }
	
	public float getPrice() { return price; }
	
	public String getName() {return name;}
}
