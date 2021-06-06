package apihttp;

/**
 * Class which contains the API HTTP functions for the user can manage his card
 * @author Mar�a
 * @author Fran
 */
import filepersistence.*;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import coreapi.Order;
import coreapi.OrderImpl;
import coreapi.OrderService;

import java.lang.String;
import java.math.BigDecimal;
import coreapi.User;
import coreapi.Card;
import coreapi.WrongTransactionException;
import filepersistence.*;
import com.example.DataBaseCruca.*;

@RestController
public class CardController 
{
	private DiskCardData DC;
	private DiskUserData DU;
	private DiskOrderData DO;
	private OrderService OService;
	private MailService MS;
	public CardController(OrderService os)
	{
		this.DC = new DiskCardData("./");
		this.DU = new DiskUserData("./");
		this.DO = new DiskOrderData("./");
		this.OService = os;
		this.MS = new MailService();
		
	}
	/**
	 * Return the balance of the user card
	 * 
	 * @param dni			the identifier of the user
	 * @param Ncard			the identifier of the card
	 * @see Card
	 * @see User
	 * @return				the current balance of an user in a card
	 */
	@GetMapping("/card/userbalance/{parameters}")
	public BigDecimal userBalance(@PathVariable("parameters") int dni, int Ncard)
	{
		Card c = DC.getCard(Ncard);
		User u = DU.getUser(dni);
		try
		{
			if(dni == c.getUserDni())
			{
				return c.getBalance();
			}
			else
			{
				throw new WrongTransactionException("User DNI and card user DNI are different");
			}
		}catch(WrongTransactionException e)
		{
			
		}
		
	}
	
	/**
	 * Add balance to a current balance in a card user.
	 * 
	 * @param nCard			the identifier of the card
	 * @param newbalance	the quantity of money which an user add to his balance.
	 * @see Card
	 * @see ReloadRepository
	 */
	@PutMapping("/card/addbalance/{parameters}")
	public void addBalance(@PathVariable("parameters") int dni, int nCard, BigDecimal newbalance)
	{
		try
		{
			Card c = DC.getCard(nCard);
			c.addBalance(dni,nCard,newbalance);
			ReloadRepository.save(new Reload(c.getUserDni(),nCard,newbalance,c.getBalance()));
			DC.saveCard(c);
		}catch(WrongTransactionException e)
		{
			
		}
	}
	
	/**
	 * Send to user and set to an order a validation code
	 * 
	 * @param dni			the identifier of the user
	 * @param idOrd			the identifier of the order
	 * @see Card
	 * @see Order
	 * @see User
	 */
	@PutMapping("/card/authpayment/{parameters}")
	public void paymentAuthoritation(@PathVariable("parameters") int dni, int idOrd)
	{
		User u = DU.getUser(dni);
		Order o = DO.getOrder(idOrd);
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();
		int charsLength = chars.length;
		Random r = new Random();
		StringBuffer b = new StringBuffer();
		for(int i = 0; i < 12; i++)
		{
			b.append(chars[r.nextInt(charsLength)]);
		}
		OService.setCode(b.toString());
		MS.sendEmail(u.getEmail(), "Validation Code", "The code to validate the order is: " + b.toString());
		DO.saveOrder(o);
	}
	
	/**
	 * Register a pay of an user at the data base
	 * 
	 * @param dni			the identifier of the user
	 * @param concpt		justification of payment
	 * @param nCard			the identifier of card
	 * @param paybalance	the quantity of the payment
	 * @see Card
	 * @see Order
	 * @see User
	 */
	@PutMapping("/card/payment/{parameters}")
	public void PayRegister(@PathVariable("parameters") int dni, String concpt, int nCard, BigDecimal paybalance)
	{
		try
		{
			Card c = DC.getCard(nCard);
			User u = DU.getUser(dni);
			c.deleteBalance(dni,nCard,paybalance);
			PaymentRepository.save(new Payment(concpt,u,paybalance));
			DC.saveCard(c);
		}catch(WrongTransactionException e)
		{
			
		}
	}
}
