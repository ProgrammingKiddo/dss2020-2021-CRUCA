package apihttp;


import java.util.List;


import coreapi.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductController {
	
	@Autowired
	private ApiHTTPService APIService;
	public ProductController(ApiHTTPService as)
	{
		this.APIService = as;
	}
	
	/* -------------------------- NEW CODE -------------------------- */

	@GetMapping("/productstypes")
	public List<String> getTypes()
	{
		return APIService.getSpecificProduct();
	}
	

	@GetMapping("/specifictypesproducts/{type}")
	public List<Product> getTypeProducts(@PathVariable("type") String type)
	{
		return APIService.getAvailableTypeProducts(type);
	}
	
	/* -------------------------- OLD CODE -------------------------- */
	/*
	@GetMapping("/productstype/{type}")
	public List<Product> getAvailabletypeProducts(@PathVariable String type) 
	{
		return coffee.getSpecificTypeProduct(type);
	}
	
	@GetMapping("/product/{id}")
	public Product getSpecificProduct(@PathVariable int idP)
	{
		return DP.getProduct(idP);
	}*/

}
