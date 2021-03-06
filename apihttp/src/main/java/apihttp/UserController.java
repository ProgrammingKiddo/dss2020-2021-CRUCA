package apihttp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import coreapi.Card;
import coreapi.Order;
import coreapi.User;


 /**
  * This class contains the functions to obtain or change information about the system users
  * 
  * @author Maria
  * @author Fran
  *
  */

@RestController
public class UserController 
{
	@Autowired
	private ApiHTTPService APIService;
	
	public UserController(ApiHTTPService as)
	{
		this.APIService = as;
	}
	
	/**
	 * Register a new user.
	 * @param u	Specific user data.
	 */
	@PostMapping("/newuser")
	public Card addUser(@RequestBody User u)
	{
		return APIService.userRegister(u);
		
	}
	
	/**
	 * Update the data of a specific user.
	 * @param u				Modified user data.
	 * @param userid		Id of the user to whom we want to update the data.
	 */
	@PutMapping("/updateUser/{userid}")
	public User updateUser(@RequestBody User u, @PathVariable int userid)
	{
		return APIService.userUpdate(u, userid);
		
	}
	
	/**
	 * Get the data of a specific user.
	 * @param userid	The id of a specific order
	 * @return			Returns the object of the specified User
	 */
	@GetMapping("/getuser/{userid}")
	public User getUser(@PathVariable int userid)
	{
		return APIService.getUser(userid);
		
	}

	/**
	 * Returns the list of all orders for a specific user.
	 * @param userid	ID of the user from whom we want to obtain the orders.
	 * @return		Returns the list of all orders for the indicated user.
	 */
	@GetMapping("/getuserorders/{userid}")
	public List<Order> userOrders(@PathVariable int userid)
	{
		return APIService.getOrders(userid);
	}
	
}
