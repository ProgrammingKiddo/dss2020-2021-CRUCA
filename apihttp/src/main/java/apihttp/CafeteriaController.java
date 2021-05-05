package apihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
public class CafeteriaController {

	private final Cafeteria coffe;
	
	CafeteriaController(Cafeteria c)
	{
		this.coffe = c;
	}
	
	@GetMapping("/cafeterias")
	String get_id()
	{
		return coffe.getId();
	}
	
	@GetMapping("/cafeterias")
	String get_name()
	{
		return coffe.getName();
	}
	
	@GetMapping("/cafeterias")
	String get_email()
	{
		return coffe.getEmail();
	}
	
	@GetMapping("/cafeterias")
	List<Product> get_aviable_products()
	{
		return coffe.getAvaiableProducts();
	}
	
	@GetMapping("/cafeterias")
	List<String> get_types()
	{
		coffe.getTypes();
	}
	
	@PatchMapping("/cafeterias/{parameters}")
	void register_product(@PathVariable("parameters") Product prod, int quantity)
	{
		coffe.registerProduct(prod,quantity);
	}
	
	@GetMapping("/cafeterias/{prod}")
	int get_products_quantity(@PathVariable Product prod)
	{
		return coffe.getProductQuantity(prod);
	}
	
	@PatchMapping("/cafeterias/{parameters}")
	void add_Product_Stock(@PathVariable("parameters") Product prod, int quantity)
	{
		coffe.addProductStock(prod,quantity);
	}
	
	@PatchMapping("/cafeterias/{parameters}")
	void remove_Product_Stock(@PathVariable("parameters") Product prod, int quantity)
	{
		coffe.removeStock(prod,quantity);
	}
	
	@DeleteMapping("/cafeterias/{prod}")
	void remove_product(@PathVariable Product prod)
	{
		coffe.remove(prod);
	}
	
	@PatchMapping("/cafeterias/{order}")
	void register_order(@PathVariable Order ord)
	{
		coffe.registerOrder(ord);
	}
	
	@GetMapping("/cafeterias")
	List<Order> get_orders()
	{
		return coffe.getOrders();
	}
	
	@PatchMapping("/cafeterias/{type}")
	void add_type(@PathVariable String type)
	{
		coffe.addType(type);
	}
	
	@PatchMapping("/cafeterias/{type}")
	void delete_type(@PathVariable String type)
	{
		coffe.DeleteType(type);
	}
	
	
	
	

}
