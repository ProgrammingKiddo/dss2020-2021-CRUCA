package apihttp.src.main.java.apihttp;
import filepersistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.src.main.java.coreapi.List;
import api.src.main.java.coreapi.LocalDate;
import api.src.main.java.coreapi.Order;
import api.src.main.java.coreapi.Period;
import api.src.main.java.coreapi.String;
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
	
	/**
	 * Obtain a specific user object by the identifier
	 * @param dni Identifier of the user
	 * @see User
	 * @see DiskUserData
	 * @return User object with this DNI
	 */
	@GetMapping("/users/profile/{dni}")
    User getUser(@PathVariable int dni)
    {
        return DiskUserData.getUser(dni);
    }
    
	/**
	 * Save an User object with data modifications
	 * 
	 * @param user User object will be saved with the new data
	 * @see User
	 * @see DiskUserData
	 */
    @PutMapping("/users/editProfile/{user}")
    void editProfile(@RequestBody User user)
    {
    	DiskUserData.saveUser(user);
    }
    
    /**
     * Obtain a list of the different Orders from an User by his dni
     * 
     * @param dni	Identifier of the user
     * @see User
     * @see Order
     * @return List of the different Orders from an User
     */
    @GetMapping("/users/getorders/{dni}")
    List<Order> getOrders(@PathVariable int dni)
    {
    	return User.getUserOrderList(dni);
    }
}
