package apihttp;

/**
 * Class representing the functions to manage the products.
 * @author Fran
 * @author Maria
 * 
 */

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
	
	/**
	 * Returns a list with the different types of products that exist.
	 * @return	Returns a list with the different types of products.
	 */
	@GetMapping("/productstypes")
	public List<String> getTypes()
	{
		return APIService.getSpecificProduct();
	}

	/**
	 * Returns a list with the products of a certain type.
	 * @param type	Specific product type.
	 * @return	Returns a list with the products of the type entered.
	 */
	@GetMapping("/specifictypesproducts/{type}")
	public List<Product> getTypeProducts(@PathVariable("type") String type)
	{
		return APIService.getAvailableTypeProducts(type);
	}

}
