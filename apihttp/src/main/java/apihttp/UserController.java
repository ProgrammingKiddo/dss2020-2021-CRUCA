package apihttp.src.main.java.apihttp;

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
	private final User user;
	
	UserController(User user)
	{
		this.user = user;
	}
	
	@GetMapping("/users")
	int get_n_card()
	{
		return user.get_n_card();
	}
	
	@GetMapping("/users")
	string get_name()
	{
		return user.get_name();
	}
	
	@GetMapping("/users")
	Period get_age()
	{
		return user.get_age();
	}
	
	@GetMapping("/users")
	string get_surname()
	{
		return user.get_surname();
	}
	
	@GetMapping("/users")
	List<Order> get_UserOrderList()
	{
		return user.get_UserOrderList();
	}
	
	@PatchMapping("/users/{parameters}")
	void set_name(@PathVariable("parameters")String Name)
	{
		user.set_name(Name);
	}
	
	@PatchMapping("/users/{parameters}")
	void set_surname(@PathVariable("parameters")String Surname)
	{
		user.set_surname(Surname);
	}
	@PatchMapping("/users/{parameters}")
	void set_n_card(@PathVariable("parameters")String N_card)
	{
		user.set_n_card(N_card);
	}
		
	@PatchMapping("/users/{parameters}")
	void set_BirthDate(@PathVariable("parameters")LocalDate newDate)
	{
		user.set_BirthDate(newDate);
	}
	
	@PatchMapping("/users/scheduleOrder/{parameters}")
	void scheduleOrder(@PathVariable("parameters")LocalDate date, LocalTime time, Order ord)
	{
		LocalDateTime scheduleDate = LocalDateTime.of(date,time);
		//Asignar esta fecha y hora a la fecha y hora del pedido
	}
		
}
