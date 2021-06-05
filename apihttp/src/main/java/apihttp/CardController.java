package apihttp.src.main.java.apihttp;

/**
 * Class which contains the API HTTP functions for the user can manage his card
 * @author Mar�a
 * @author Fran
 */
import filepersistence;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.src.main.java.coreapi.List;
import api.src.main.java.coreapi.LocalDate;
import api.src.main.java.coreapi.Order;
import api.src.main.java.coreapi.Period;
import api.src.main.java.coreapi.String;

@RestController
public class CardController 
{
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
		Card c = Load.LoadCard(Ncard);
		User u = Load.LoadUser(dni);
		try
		{
			if(dni == c.getUserDni())
			{
				return c.getBalance();
			}
			else
			{
				throw WrongTransactionException("User DNI and card user DNI are different");
			}
		}catch(WrongTransactionException e)
		
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
	public void addBalance(@PathVariable("parameters") int nCard, BigDecimal newbalance)
	{
		try
		{
			Card c = Load.LoadCard(nCard);
			c.addBalance(newbalance);
			ReloadRepository.save(new Reload(c.getUserDni,nCard,newbalance,c.getBalace()));
			Save.SaveCard(c);
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
		User u = Load.LoadUser(dni);
		Order o = Load.LoadOrder(iOrd);
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();
		int charsLength = chars.length;
		Random r = new Random();
		StringBuffer b = new StringBuffer();
		for(int i = 0; i < 12; i++)
		{
			b.append(chars[r.nextInt(charsLength)]);
		}
		o.setCode(b.toString());
		MailService.sendEmail(u.getEmail(), "Validation Code", "The code to validate the order is: " + b.toString());
		Save.SaveOrder(o);
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
			Card c = Load.LoadCard(nCard);
			User u = Load.LoadUser(dni);
			c.deleteBalance(newbalance);
			PaymentRepository.save(new Payment(concpt,u,paybalance));
			Save.SaveCard(c);
		}catch(WrongTransactionException e)
		{
			
		}
	}
}
