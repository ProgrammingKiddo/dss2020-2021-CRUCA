package apihttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
public class OrderController {

	private final OrderService OService;
	
	Orderhttp(OrderService os){
		this.OService = os;
	}
	
	@PatchMapping("/orders/{parameters}")
	public void addProductToOrder(@PathVariable("parameters") Cafeteria coffe, Order ord, int ProductId, int Quantity)
	{
		Oservice.addProductToOrder(coffe,ord,ProductId,Quantity);
	}
	
	@DeleteMapping("/orders/{parameters}")
	public void removeProductFromOrder(@PathVariable("parameters") Order ord, int ProductId, int Quantity)
	{
		Oservice.removeProductFromOrder(ord,ProductId,Quantity);
	}
	
	@PatchMapping("/orders/{parameters}")
	public void changeStatus(@PathVariable("parameters") Order ord, OrderStatus status)
	{
		switch(status)
		{
			case IN_KITCHEN:
				OService.OrderStatus_InKitchen(ord);
			case DELIVERED:
				OService.OrderStatus_Delivered(ord);
			case PAYED:
				OService.OrderStatus_Payed(ord);
			case FINISHED:
				OService.OrderStatus_Finished(ord);
		}
	}
	
	@GetMapping("/orders/{parameters}")
	public String DailyRegister(@PathVariable("parameters") Cafeteria coffe, LocalDate date)
	{
		return "La caja de la fecha introducida es: " + OService.getTotalDailyRegister(coffe, date) + " y el número de pedidos ha sido: " + Oservice.getNumberOfDailyOrders(coffe,date);
	}

}
