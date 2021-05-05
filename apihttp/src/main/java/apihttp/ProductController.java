package apihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
public class ProductController {

	private ProductImpl prod;
	
	public ProductController(ProductImpl p)
	{
		this.prod = p;
	}
	
	@GetMapping("/products")
	public int get_id() 
	{
		return prod.getId();
	}
	
	@GetMapping("/products")
	public BigDecimal get_price()
	{
		return prod.getPrice();
	}
	
	@GetMapping("/products")
	public String get_name()
	{
		return prod.getName();
	}
	
	@GetMapping("/products")
	public String get_type()
	{
		return prod.getType();
	}

}
