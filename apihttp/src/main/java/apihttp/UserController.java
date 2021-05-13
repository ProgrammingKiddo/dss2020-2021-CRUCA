package apihttp.src.main.java.apihttp;
import filepersistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.src.main.java.coreapi.List;
import api.src.main.java.coreapi.LocalDate;
import api.src.main.java.coreapi.Order;
import api.src.main.java.coreapi.Period;
import api.src.main.java.coreapi.String;

@RestController
public class UserController 
{
	@GetMapping("/users/profile/{dni}")
    User getUser(@PathVariable int dni)
    {
        return Load.LoadUser(dni);
    }
    
    @PutMapping("/users/editProfile/{user}")
    void editProfile(@RequestBody User user)
    {
        Save.saveUser(user);
    }
	
}
