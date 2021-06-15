package apihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import coreapi.Order;
import java.lang.String;
import coreapi.User;
import filepersistence.DiskUserData;

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
	private ApiHTTPService APIService;
	public UserController()
	{
		this.APIService = new ApiHTTPService();
	}
	
	/* -------------------------- NEW CODE -------------------------- */

	@PostMapping("/newuser")
	public void addUser(@RequestBody User u)
	{
		APIService.userRegister(u);
	}
	
	@PutMapping("/updateUser/{userid}")
	public void updateUser(@RequestBody User u, @PathVariable int userid)
	{
		APIService.userUpdate(u, userid);
	}

	@GetMapping("/getuserorders/{userid}")
	public List<Order> userOrders(@PathVariable int userid)
	{
		return APIService.getOrders(userid);
	}
	
	/* -------------------------- OLD CODE -------------------------- */
	
	/**
	 * Obtain a specific user object by the identifier
	 * @param dni Identifier of the user
	 * @see User
	 * @see DiskUserData
	 * @return User object with this DNI
	 */
	/*
	@GetMapping("/users/profile/{dni}")
    User getUser(@PathVariable int dni)
    {
        return DU.getUser(dni);
    }
    */
	/**
	 * Save an User object with data modifications
	 * 
	 * @param user User object will be saved with the new data
	 * @see User
	 * @see DiskUserData
	 */
	/*
    @PutMapping("/users/editProfile/{user}")
    void editProfile(@RequestBody User user)
    {
    	DU.saveUser(user);
    }
    */
    /**
     * Obtain a list of the different Orders from an User by his dni
     * 
     * @param dni	Identifier of the user
     * @see User
     * @see Order
     * @return List of the different Orders from an User
     */
	/*
    @GetMapping("/users/getorders/{dni}")
    List<Order> getOrders(@PathVariable int dni)
    {
    	User u = DU.getUser(dni);
    	return u.getUserOrderList();
    }
    */
}
