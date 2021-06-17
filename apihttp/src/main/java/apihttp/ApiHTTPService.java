package apihttp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coreapi.Cafeteria;
import coreapi.InsufficientStockException;
import coreapi.InvalidDateException;
import coreapi.Order;
import coreapi.OrderFactory;
import coreapi.OrderService;
import coreapi.Product;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;
import coreapi.User;
import data.OrderData;
import data.ProductData;
import data.UserData;


@Service
public class ApiHTTPService {
	
	@Autowired
	private Cafeteria coffee;
	@Autowired
	private OrderData DO;
	@Autowired
	private ProductData DP;
	@Autowired
	private UserData DU;
	@Autowired
	private MailService MS;
	@Autowired
	private OrderService OService;
	
	public ApiHTTPService(Cafeteria cf, OrderData dord,ProductData dp,  UserData du, MailService ml, OrderService os)
	{
		this.coffee = cf;
		this.DO = dord;
		this.DP = dp;
		this.DU = du;
		this.MS = ml;
		this.OService = os;
	}
	
	/* CreateNewOrder */
	
	public void createOrder()
	{
		DO.saveOrder(OrderFactory.createOrder(coffee,LocalDateTime.now()));
	}
	
	/* AvailableProductsTypes */
	
	public List<Product> getAvailableTypeProducts(String type)
	{
		return coffee.getSpecificTypeProduct(type);
	}
	
	/* AvailableProductsOfATypes */
	
	public List<String> getSpecificProduct()
	{
		return coffee.getTypes();
	}
	
	/* AddProductToOrder */
	
	public void addProductToOrder(int ordID, int prodID, int q) //RequestBody mapa, pathvariable idpedido
	{
		try
		{
			OService.addProductToOrder(coffee,DO.getOrder(ordID),DP.getProduct(prodID), q);
		}catch(InsufficientStockException ex)
		{
			
		}
		
	}
	/* DeleteProductFromOrder */
	
	public void removeProductFromOrder(int ordID, int prodID, int q)//RequestBody mapa, pathvariable idpedido
	{
		try
		{
			OService.removeProductFromOrder(coffee,DO.getOrder(ordID),DP.getProduct(prodID), q);
		}catch(InsufficientStockException ex)
		{
			
		}
		catch (ProductNotContainedInOrderException ex)
		{
			
		}
	}
	
	/* FinishOrder */
	
	public void closeOrder(int ordID)
	{
		try
		{
			OService.OrderStatus_Finished(DO.getOrder(ordID));
		}catch(UnreachableStatusException ex)
		{
			
		}
	}
	/* DailyRegister */
	
	public String DailyRegister(LocalDate date)
	{
		try
		{
			return "La caja de la fecha introducida es: " + OService.getTotalDailyRegister(coffee, date) + " y el n�mero de pedidos ha sido: " + OService.getNumberOfDailyOrders(coffee,date);
		}catch(InvalidDateException e)
		{
			return e.toString();
		}
		
	}
	
	/* UserRegister */
	
	public void userRegister(User u)
	{
		DU.saveUser(u);
	}
	
	/* UserUpdate */
	
	public void userUpdate(User CopyUser, int userID)
	{
		User u = DU.getUser(userID);
		u.setDNI(CopyUser.getDni());
		u.setNcard(CopyUser.getNcard());
		u.setName(CopyUser.getName());
		u.setSurname(CopyUser.getSurname());
		u.setBirthDate(CopyUser.getBirthDate());
		u.setEmail(CopyUser.getEmail());
	}
	
	/* OrderProgramming */
	
	public void OrderProgramming(int ordID, LocalDateTime PD)
	{
		try
		{
			if(OService.getValidationStock(DO.getOrder(ordID), coffee))
			{
				OService.setProgrammingDate(DO.getOrder(ordID), PD);
			}
			else
			{
				throw new InsufficientStockException("There isn't enough stock of any of your products");
			}
		}catch(InsufficientStockException ex)
		{
			
		}
		
	}
	
	/* KitchenNotify */
	
	public void kitchenNotification(int ordID)
	{
		Order o = DO.getOrder(ordID);
		MS.sendEmail(coffee.getEmail(),"Order " + o.getId(), "This order is programming" + " to the date: " + OService.getProgrammingDate(o));
	}
	
	/* UserOrders */
	
	List<Order> getOrders(int userID)
	{
		User u = DU.getUser(userID);
		return u.getUserOrderList();
	}
}
