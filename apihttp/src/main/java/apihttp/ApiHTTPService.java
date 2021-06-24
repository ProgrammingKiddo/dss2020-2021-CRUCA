package apihttp;

/**
 * Contains the implementation of the functions used by the controllers.
 * @author Fran
 * @author Maria
 * 
 */
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import coreapi.Cafeteria;
import coreapi.Card;
import coreapi.InsufficientStockException;
import coreapi.InvalidDateException;
import coreapi.Order;
import coreapi.OrderFactory;
import coreapi.OrderService;
import coreapi.OrderStatus;
import coreapi.Product;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;
import coreapi.User;
import coreapi.WrongTransactionException;
import data.CafeteriaData;
import data.CardData;
import data.OrderData;
import data.ProductData;
import data.UserData;


@Service
public class ApiHTTPService {
	
	@Autowired
	private Cafeteria coffee;
	@Autowired
	private CafeteriaData CD;
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
	@Autowired
    private CardData DC;
	
	public ApiHTTPService(Cafeteria cf, CafeteriaData dcof, OrderData dord,ProductData dp,  UserData du, MailService ml, OrderService os, CardData cd)
	{
		this.coffee = cf;
		this.DO = dord;
		this.DP = dp;
		this.DU = du;
		this.MS = ml;
		this.OService = os;
        this.DC = cd;
        this.CD = dcof;
	}
	
	
	/**
	 * Create a new order for a specific user.
	 * @param iduser	The specific user id.
	 */
	public void createOrder(int iduser)
	{
		User u = DU.getUser(iduser);
	    Order o = OrderFactory.createOrder(coffee,LocalDateTime.now());
	    u.setOrder(o);
	    DO.saveOrder(o);
	    CD.saveCafeteria(coffee);
	    DU.saveUser(u);
	}
	
	public Order getOrder(int orderid)
	{
		return DO.getOrder(orderid);
	}

	/**
	 * Returns a list with the products of a certain type.
	 * @param type	Specific product type.
	 * @return		Returns a list with the products of the type entered.
	 */
	public List<Product> getAvailableTypeProducts(String type)
	{
		return coffee.getSpecificTypeProduct(type);
	}
	
	/**
	 * Returns a list with the different types of products that exist.
	 * @return	Returns a list with the different types of products.
	 */
	public List<String> getProductTypes()
	{
		return coffee.getTypes();
	}
	
	public int getProductQuantity(int prodId)
	{
		return coffee.getProductQuantity(DP.getProduct(prodId));
	}
	
	/**
	 * Add a product to a current order.
	 * @param ordID		The id of a current Order which the product will be added.
	 * @param prodID	The id of the product that we want to add to the order.
	 * @param q			Quantity of product to add to the order.
	 * @throws InsufficientStockException	If the quantity to remove is bigger than 
	 * 										the quantity which is stock in the order.
	 * 
	 */
	public void addProductToOrder(int ordID, int prodID, int q) throws InsufficientStockException
	{
		Product p = DP.getProduct(prodID);
		Order o = DO.getOrder(ordID);
		try
		{
			OService.addProductToOrder(coffee, o, p, q);
		}
		catch(InsufficientStockException ex)
		{
			System.out.println("Excepcion!!!!");
		}
		
	}

	/**
	 * Delete a quantity of product from an order.
	 * @param ordID		The id of a current Order which the product will be remove.
	 * @param prodID	The id of the product that we want to remove to the order.
	 * @param q			Quantity of product to remove to the order.
	 * @throws InsufficientStockException			If the quantity to remove is bigger than 
	 * 												the quantity which is stock in the order.
	 * @throws ProductNotContainedInOrderException	If the product isn't in the basket.
	 */
	public void removeProductFromOrder(int ordID, int prodID, int q) throws InsufficientStockException,ProductNotContainedInOrderException
	{
		try
		{
			OService.removeProductFromOrder(coffee, DO.getOrder(ordID), DP.getProduct(prodID), q);
		}catch(InsufficientStockException ex)
		{
			
		}
		catch (ProductNotContainedInOrderException ex)
		{
			
		}
	}
	

	/**
	 * Change the order status to finished.
	 * @param ordID	ID of the order to which we want to change the status.
	 * @throws UnreachableStatusException	If the conditions to set the <code>PAYED</code> status aren't met.
	 */
	public void closeOrder(int ordID) throws UnreachableStatusException
	{
		try
		{
			OService.OrderStatus_Finished(DO.getOrder(ordID));
		}
		catch(UnreachableStatusException ex)
		{
			
		}
	}
	
	/**
	 * Returns the total in euros and the total orders of the box of the day of a specific date.
	 * @param date	Date from which to check the box of the day.
	 * @return	Returns the total in euros and the total orders of the box of the day of the given date.
	 */
	public String DailyRegister(LocalDate date)
	{
		try
		{
			return "La caja de la fecha introducida es: " + OService.getTotalDailyRegister(coffee, date).doubleValue() + " y el numero de pedidos ha sido: " + OService.getNumberOfDailyOrders(coffee,date);
		}catch(InvalidDateException e)
		{
			return e.toString();
		}
		
	}

	/**
	 * Register a new user.
	 * @param u		Specific user data.
	 */
	public void userRegister(User u)
	{
		DU.saveUser(u);
		Card c = new Card(u.getDni(),u.getNcard());
		DC.saveCard(c);
	}
	
	/**
	 * Update the data of a specific user.
	 * @param CopyUser	Modified user data.
	 * @param userID	Id of the user to whom we want to update the data.
	 */
	public User userUpdate(User CopyUser, int userID)
	{
		User u = DU.getUser(userID);
		u.setDNI(CopyUser.getDni());
		u.setNcard(CopyUser.getNcard());
		u.setName(CopyUser.getName());
		u.setSurname(CopyUser.getSurname());
		u.setBirthDate(CopyUser.getBirthDate());
		u.setEmail(CopyUser.getEmail());
		DU.saveUser(u);
		return u;
	}
	
	public User verusuario(int userID)
	{
		return DU.getUser(userID);
	}
	/**
	 * Schedule an order for a specific date and time.
	 * @param ordID		ID of the order to be scheduled.
	 * @param PD		Date and time the order is scheduled.
	 */
	public void OrderProgramming(int ordID, LocalDateTime PD) throws InvalidDateException
	{
		try
		{
			OService.setProgrammingDate(DO.getOrder(ordID), PD);
		}
		catch(InvalidDateException e) {}
			
	}

	/**
	 * Notify the kitchen with the order information.
	 * @param ordID	ID of the order for which you want to send the information.
	 */
	public void kitchenNotification(int ordID)
	{
		Order o = DO.getOrder(ordID);
		MS.sendEmail(coffee.getEmail(),"Order " + o.getId(), "This order is programming" + " to the date: " + OService.getProgrammingDate(o));
	}
	

	/**
	 * Returns the list of all orders for a specific user.
	 * @param userID	ID of the user from whom we want to obtain the orders.
	 * @return	Returns the list of all orders for the indicated user.
	 */
	List<Order> getOrders(int userID)
	{
		User u = DU.getUser(userID);
		List<Integer> userOrdersId = u.getUserOrderList();
		List<Order> userOrders = new ArrayList<Order>();
		for(Integer oID : userOrdersId)
		{
			userOrders.add(DO.getOrder(oID.intValue()));
		}
		return userOrders;
	}
	
	/**
	 * Carry out the corresponding validations to be able to complete a specific order.
	 * @param idord		User ID whose order we want to complete.
	 * @param iduser	ID of the user's order which we want to complete.
	 */
	public void completeOrder(int idord, int iduser)
	{
		Order o = DO.getOrder(idord);
	    User u = DU.getUser(iduser); 
	    Card c = DC.getCard(u.getNcard());
	    RestTemplate rt = new RestTemplate();
	    if(OService.getValidationStock(o,coffee))
	    {
	    	BigDecimal s = rt.getForObject("http:://localhost:8080/userbalance/" + iduser, BigDecimal.class ,c);
	        if(((s.subtract(o.totalCost())).compareTo(new BigDecimal(-10)) >= 0) == true)
	        {
	        	rt.put("http:://localhost:8080/payauthoritation/" + iduser , o);
	        }
	    }
	}

	/**
	 * Cancel and remove an user's order.
	 * @param userId	User who cancels the order.
	 * @param idord		Contains the order id which will be delete.
	 */
	public void deleteOrderFromUser(int userId, int idord) 
	{
		Order o = DO.getOrder(idord);
		User u = DU.getUser(userId);
	    Card c = DC.getCard(u.getNcard());
	    if(o.getStatus() != OrderStatus.FINISHED)
	    {
	    	u.deleteOrder(o);
	        Map<Product,Integer> bk = o.getBasket();
	        try
	        {
	        	for(Map.Entry<Product,Integer> entry: bk.entrySet())
		        {
	        		coffee.addProductStock(entry.getKey(),entry.getValue().intValue());
		        }
		        if(o.getStatus() == OrderStatus.PAYED)
		        {
		        	c.addBalance(c.getCardNumber(),o.totalCost());
		        }
	        }
	        catch(WrongTransactionException e)
	        {
	            	
	        }
	   }
	    DO.saveOrder(o);
	    DC.saveCard(c);
	    DU.saveUser(u);
	}
	
	/**
	 * Returns the products of an order with their quantity.
	 * @param ordid		ID of the order from which we want to consult the products.
	 * @return			Returns the map of products.
	 */
	public Map<String,Integer> getProductOfOrder(int ordid)
    {
        Order o = DO.getOrder(ordid);
        Map<String, Integer> list = new LinkedHashMap<String,Integer>();
        for(Map.Entry<Product, Integer> entry: o.getBasket().entrySet())
        {
        	list.put(entry.getKey().getName(), entry.getValue().intValue());
        }
        return list;
    }
	
	
}
