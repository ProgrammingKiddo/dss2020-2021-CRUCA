package apihttp;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import coreapi.Cafeteria;
import coreapi.InsufficientStockException;
import coreapi.Order;
import coreapi.OrderService;
import coreapi.OrderStatus;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;

@RestController
public class OrderController {

	private final OrderService OService;
	
<<<<<<< Updated upstream
	public Orderhttp(OrderService os){
=======
	OrderController(OrderService os){
>>>>>>> Stashed changes
		this.OService = os;
	}
	
	@PatchMapping("/orders/{parameters}")
	public void addProductToOrder(@PathVariable("parameters") Cafeteria coffe, Order ord, int ProductId, int Quantity)
	{
		try {
			OService.addProductToOrder(coffe,ord,ProductId,Quantity);			
		} catch (InsufficientStockException ex)
		{
			
		}
	}
	
	@DeleteMapping("/orders/{parameters}")
	public void removeProductFromOrder(@PathVariable("parameters") Order ord, int ProductId, int Quantity)
	{
		try {
			OService.removeProductFromOrder(ord,ProductId,Quantity);		
		} catch (InsufficientStockException ex)
		{
			
		}
		catch (ProductNotContainedInOrderException ex)
		{
			
		}
	}
	
	@PatchMapping("/orders/{parameters}")
	public void changeStatus(@PathVariable("parameters") Order ord, OrderStatus status)
	{
		try {
			switch(status)
			{
				case IN_KITCHEN:
					OService.OrderStatus_InKitchen(ord);
					break;
				case DELIVERED:
					OService.OrderStatus_Delivered(ord);
					break;
				case PAYED:
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
	}
	
	@GetMapping("/orders/{parameters}")
	public String DailyRegister(@PathVariable("parameters") Cafeteria coffe, LocalDate date)
	{
		return "La caja de la fecha introducida es: " + OService.getTotalDailyRegister(coffe, date) + " y el n�mero de pedidos ha sido: " + OService.getNumberOfDailyOrders(coffe,date);
	}

}
