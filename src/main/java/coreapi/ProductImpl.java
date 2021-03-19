package coreapi;

public class ProductImpl implements Product {

	private int id;
	private float price;
	private int stock;
	private String name;
	
	
	public ProductImpl(int id, float price, int stock, String name)
	{
		this.id = id;
		this.price = price;
		this.stock = stock;
		this.name = name;
	}
	
	public int getId() { return id; }
	
	public float getPrice() { return price; }
	
	public int getStock() { return stock; }
	
	public String getName() {return name;}
}
