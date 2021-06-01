package apihttp.src.main.java.apihttp;

/**
 * @author María
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
	
	@PostMapping("/card/addbalance/{parameters}")
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
	
	@PatchMapping("/card/authpayment/{parameters}")
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
	
	@PostMapping("/card/payment/{parameters}")
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
