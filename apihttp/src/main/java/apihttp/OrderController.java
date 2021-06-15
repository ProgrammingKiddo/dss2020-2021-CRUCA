package apihttp;
/**
 * @author Mar�a
 * @author Fran
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import coreapi.Cafeteria;
import coreapi.Card;
import coreapi.InsufficientStockException;
import coreapi.InvalidDateException;
import coreapi.OrderImpl;
import coreapi.Order;
import coreapi.OrderFactory;
import coreapi.OrderService;
import coreapi.OrderStatus;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;
import coreapi.User;
import filepersistence.DiskOrderData;
import filepersistence.DiskProductData;


/**
 * Class representing the functions which user can manage his orders
 * @author María
 * @author Fran
 * @version 0.1
 */
@RestController
public class OrderController {

	private ApiHTTPService APIService;
	public OrderController()
	{
		this.APIService = new ApiHTTPService();
	}
	
	/* -------------------------- NEW CODE -------------------------- */

	@PostMapping("/createorder")
	public void createNewOrder()
	{
		APIService.createOrder();
	}

	@PutMapping("/addproduct/{ordid}")
	public void addProductToOrder(@RequestBody Map<Integer,Integer> pq, @PathVariable("ordid") int ordid)
	{
		Map.Entry<Integer, Integer> entry = pq.entrySet().iterator().next();
		APIService.addProductToOrder(ordid, entry.getKey().intValue(), entry.getValue().intValue());
	}

	@PutMapping("/removeproduct/{ordid}")
	public void removeProductToOrder(@RequestBody Map<Integer,Integer> pq, @PathVariable("ordid") int ordid)
	{
		Map.Entry<Integer, Integer> entry = pq.entrySet().iterator().next();
		APIService.removeProductFromOrder(ordid, entry.getKey().intValue(), entry.getValue().intValue());
	}

	@PutMapping("/finishorder/{ordid}")
	public void finishOrder(@PathVariable("ordid") int ordid)
	{
		APIService.closeOrder(ordid);
	}

	@GetMapping("/dailyregister/{date}")
	public String dailyRegister(@PathVariable("date") LocalDate date)
	{
		return APIService.DailyRegister(date);
	}

	@PutMapping("/programmingdate/{ordid}")
	public void programmingDate(@RequestBody LocalDateTime PD, @PathVariable("ordid") int ordid)
	{
		APIService.OrderProgramming(ordid, PD);
	}

	@PutMapping("/kitchennotify/{ordid}")
	public void kitchenNotify(@PathVariable("ordid") int ordid)
	{
		APIService.kitchenNotification(ordid);
	}
	
	/* -------------------------- OLD CODE -------------------------- */
	
	//@PostMapping("/createorder/{coffee}")
	
	/**
	 * Add a product to a current order
	 * @param coffe		Contains the current Cafeteria object
	 * @param ord		Contains the current Order object which the product will be added
	 * @param ProductId The identifier of the product
	 * @param Quantity	The quantity of the product will be added
	 * @see InsuffincientStockException
	 * @see Cafeteria
	 * @see Order
	 * @see Product
	 */
	/*
	@PatchMapping("/orders/{parameters}")
	public void addProductToOrder(@PathVariable("parameters") Cafeteria coffe, Order ord, int ProductId, int Quantity)
	{
		try {
			OService.addProductToOrder(coffe,ord,DP.getProduct(ProductId),Quantity);			
		} catch (InsufficientStockException ex)
		{
			
		}
	}
	*/
	/**
	 * Delete a quantity of product from an order
	 * @param ord			Contains the current Order object which the product will be deleted
	 * @param ProductId		The identifier of the product
	 * @param Quantity		The quantity of the product will be deleted
	 * @see Product
	 * @see Order
	 * @see InsuffincientStockException
	 */
	/*
	@DeleteMapping("/orders/{parameters}")
	public void removeProductFromOrder(@PathVariable("parameters") Cafeteria coffee, Order ord, int ProductId, int Quantity)
	{
		try {
			OService.removeProductFromOrder(coffee,ord,DP.getProduct(ProductId),Quantity);		
		} catch (InsufficientStockException ex)
		{
			
		}
		catch (ProductNotContainedInOrderException ex)
		{
			
		}
	}*/
	
	/**
	 * Change the status of an order
	 * 
	 * @param user		Contains the user for obtain his DNI
	 * @param c			Contains the user card for obtain his number card
	 * @param ord		Contains the order which is going to be changed 
	 * @param status	Status that is going to be put in the order
	 * @param coffee	Contains the Cafeteria for obtain the email address
	 * @see User
	 * @see Card
	 * @see Order
	 * @see OrderStatus
	 * @see Cafeteria
	 */
	/*
	@PatchMapping("/orders/{parameters}")
	public void changeStatus(@PathVariable("parameters") User user, Card c, Order ord, OrderStatus status, Cafeteria coffee, LocalDateTime PD)
	{
		try {
			switch(status)
			{
				case IN_KITCHEN:
					if(OService.getValidationStock(ord,coffee))
					{	
						OService.OrderStatus_InKitchen(ord);
						OService.setProgrammingDate(ord,PD);
						MS.sendEmail(coffee.getEmail(),"Order " + ord.getId(), "This order is programming"
							+ " to the date: " + OService.getProgrammingDate(ord));
					}
					else
					{
						throw new UnreachableStatusException("There isn't enough stock of any of your products");
					}
					break;
				case DELIVERED:
					OService.OrderStatus_Delivered(ord);
					break;
				case PAYED:
					RestTemplate rt = new RestTemplate();
					boolean cft;
					BigDecimal bl = rt.getForObject("http://localhost:8080/apihttp/card/userbalance/{parameters}",BigDecimal,user.getDni(),c.getCardNumber());
					if((bl - ord.totalCost()) >= -10)
					{
						rt.put("http://localhost:8080/apihttp/card/authpayment/{parameters}",Order.class,user.getDni(),ord.getId());
						cft = rt.getForObject("http://localhost:8080/apihttp/order/payauth/
						if(cft == true)
						{
							OService.OrderStatus_Payed(ord);
						}
					}
					OService.OrderStatus_Payed(ord);
					break;
				case FINISHED:
					OService.OrderStatus_Finished(ord);
					break;
				default:
					break;
			}
		} catch (UnreachableStatusException ex)
		{
			
		}
	}*/
	
	/**
	 * Show the number of the orders registered and the total amount of money earned
	 * 
	 * @param coffe Contains the coffe object for obtain the orders of the days
	 * @param date	Contains the date which we want to obtain the orders
	 * @return	The number of the orders registered and the total amount of money earned
	 * @see Cafeteria
	 * @see LocalDate
	 * @see OrderService
	 */
	/*
	@GetMapping("/orders/{parameters}")
	public String DailyRegister(@PathVariable("parameters") Cafeteria coffe, LocalDate date)
	{
		try
		{
			return "La caja de la fecha introducida es: " + OService.getTotalDailyRegister(coffe, date) + " y el n�mero de pedidos ha sido: " + OService.getNumberOfDailyOrders(coffe,date);
		}catch(InvalidDateException e)
		{
			return e.toString();
		}
	}
	*/
	/**
	 * Compare the Order validation code with a validation coder introduced by the user
	 * 
	 * @param Ord Contains the Order object to obtain his code
	 * @param code The code introduced by the user
	 * @see Order
	 * @return true if the received code and the order code are equal and false otherwise
	 */
	/*
	@GetMapping("/order/payauth/{parameters}")
	public boolean payConfirmation(@PathVariable("parameters") Order Ord, String code)
	{
		return OService.getValidationCode(Ord).equalsIgnoreCase(code);
	}
	*/
	/**
	 * Cancel an user's order
	 * 
	 * @param ord	contains the order which will be delete
	 * @param c		contains the card to return the money to the user
	 * @see Order
	 * @see Card
	 * @see LocalDateTime
	 */
	
	/*
	@DeleteMapping("/order/cancelorder/{parameters}")
	public void cancelOrder(@PathVariable("parameters") Order ord, Card c)
	{
		File order = new File(path + "Order" + ord.getId() + ".txt");
		RestTemplate rt = new RestTemplate();
		try 
		{
			if((ord.getProgrammingDate().until(LocalDateTime.now().MINUTES) >= 30))
			{
				if(ord.getStatus() == OrderStatus.PAYED)
				{
					rt.put("http://localhost:8080/apihttp/card/addbalance/{parameters}",Card.class,c.getNumberCard(),ord.totalCost());
				}
				order.delete();
			}
			else
			{
				throw WrongTransactionException("There isnt enough time to cancel the order");
			}
		}catch(WrongTransactionException e)
		{
			
		}
	}*/
	
}
