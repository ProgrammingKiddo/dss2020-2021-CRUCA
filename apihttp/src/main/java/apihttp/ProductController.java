package apihttp;

import java.math.BigDecimal;
import java.util.List;

import coreapi.Cafeteria;
import coreapi.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import filepersistence.DiskProductData;

@RestController
public class ProductController {
	
	private Cafeteria coffee;
	private DiskProductData DP;
	
	public ProductController(Cafeteria C)
	{
		this.coffee = C;
		this.DP = new DiskProductData("./");
	}
	
	@GetMapping("/productstype/{type}")
	public List<Product> getAvailabletypeProducts(@PathVariable String type) 
	{
		return coffee.getSpecificTypeProduct(type);
	}
	
	@GetMapping("/product/{id}")
	public Product getSpecificProduct(@PathVariable int idP)
	{
		return DP.getProduct(idP);
	}

}
