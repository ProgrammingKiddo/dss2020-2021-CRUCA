package apihttp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import coreapi.InsufficientStockException;
import coreapi.Order;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;
import coreapi.User;

/**
 * Class representing the functions which user can manage his orders.
 * @author María
 * @author Fran
 * @version 0.1
 */
@RestController
public class OrderController {
	
	@Autowired
	private ApiHTTPService APIService;
	
	public OrderController(ApiHTTPService as)
	{
		this.APIService = as;
	}
	
	/**
	 * Create a new order for a specific user.
	 * @param iduser	The specific user id.
	 */
	@PostMapping("/createorder/{iduser}")
	public void createNewOrder(@PathVariable("iduser") int iduser)
	{
		APIService.createOrder(iduser);
	}
	
	@GetMapping("getorder/{orderid}")
	public Order getOrder(@PathVariable("orderid") int orderid)
	{
		return APIService.getOrder(orderid);
	}
	
	/**
	 * Add a product to a current order.
	 * @param pq	Map containing the identification of a product and the quantity to add.
	 * @param ordid	The id of a current Order which the product will be added.
	 * @throws InsufficientStockException 
	 */
	/*@PutMapping("/addproduct/{ordid}")
	public void addProductToOrder(@RequestBody Map<Integer,Integer> pq, @PathVariable("ordid") int ordid) throws InsufficientStockException
	{
		Map.Entry<Integer, Integer> entry = pq.entrySet().iterator().next();
		APIService.addProductToOrder(ordid, entry.getKey().intValue(), entry.getValue().intValue());
	}*/
	
	@PutMapping("/addproduct/{ordid}")
	public void addProductToOrder(int productId, int quantity, @PathVariable("ordid") int ordid) throws InsufficientStockException
	{
		APIService.addProductToOrder(ordid, productId, quantity);
	}

	/**
	 * Delete a quantity of product from an order.
	 * @param pq	Map containing the identification of a product and the quantity to remove.
	 * @param ordid	The id of a current Order which the product will be remove.
	 * @throws ProductNotContainedInOrderException 
	 * @throws InsufficientStockException 
	 */
	@PutMapping("/removeproduct/{ordid}")
	public void removeProductToOrder(@RequestBody Map<Integer,Integer> pq, @PathVariable("ordid") int ordid) throws InsufficientStockException, ProductNotContainedInOrderException
	{
		Map.Entry<Integer, Integer> entry = pq.entrySet().iterator().next();
		APIService.removeProductFromOrder(ordid, entry.getKey().intValue(), entry.getValue().intValue());
	}

	/**
	 * Change the order status to finished.
	 * @param ordid	ID of the order to which we want to change the status.
	 * @throws UnreachableStatusException 
	 */
	@PutMapping("/finishorder/{ordid}")
	public void finishOrder(@PathVariable("ordid") int ordid) throws UnreachableStatusException
	{
		APIService.closeOrder(ordid);
	}

	/**
	 * Show the number of the orders registered and the total amount of money earned.
	 * @param date Contains the date which we want to obtain the orders.
	 * @return	The number of the orders registered and the total amount of money earned.
	 */
	@GetMapping("/dailyregister/")
	public String dailyRegister(String date)
	{
		return APIService.DailyRegister(LocalDate.parse(date));
	}

	/**
	 * Schedule an order for a specific date and time.
	 * @param PD		Date and time the order is scheduled.
	 * @param ordid		ID of the order to be scheduled.
	 */
	@PutMapping("/programmingdate/{ordid}")
	public void programmingDate(String programmingDateTime, @PathVariable("ordid") int ordid)
	{
		APIService.OrderProgramming(ordid, LocalDateTime.parse(programmingDateTime));
	}

	/**
	 * Notify the kitchen with the order information.
	 * @param ordid		ID of the order for which you want to send the information.
	 */
	@PutMapping("/kitchennotify/{ordid}")
	public void kitchenNotify(@PathVariable("ordid") int ordid)
	{
		APIService.kitchenNotification(ordid);
	}
	
	/**
	 * Carry out the corresponding validations to be able to complete a specific order.
	 * @param ou	Map containing the id of the user and the id of the order to be completed.
	 */
	@PutMapping("/completeorder/")
	public void completeOrder(@RequestBody Map<Integer,Integer> ou)
	{
		Map.Entry<Integer, Integer> entry = ou.entrySet().iterator().next();
	    APIService.completeOrder(entry.getKey().intValue(),entry.getValue().intValue());
	}

	/**
	 * Cancel and remove an user's order.
	 * @param userId		User who cancels the order.
	 * @param idord	Contains the order id which will be delete.
	 */
	@PutMapping("/deleteuserord/{idord}")
	public void deleteOrderFromUser(int userId, @PathVariable("idord") int idord)
	{
		APIService.deleteOrderFromUser(userId ,idord);
	}
	
}
