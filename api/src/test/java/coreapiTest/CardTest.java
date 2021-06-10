/**
 * @author María
 */
package coreapiTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import coreapi.*;

public class CardTest 
{
	private User user = new User("Alberto","Lozano",123456789,LocalDate.parse("1996-05-20"), 45678904,"albertolozano@gmail.com");
	private Card card = new Card (45678904,123456789);
	
	@Test
	public void DniCheckCard()
	{
		Assert.assertEquals("DifferentDniAtUserCard", 45678904, card.getUserDni());
	}
	
	@Test
	public void NcardCheckCard()
	{
		Assert.assertEquals("DifferentNcardAtUserCard", 123456789, card.getCardNumber());
	}
	
	@Test
	public void BalanceCheckCard()
	{
		Assert.assertEquals("DifferentBalanceAtUserCard", 0, card.getBalance());
	}
	
	@Test
	public void AddBalanceCheckCard()
	{
		BigDecimal bal = card.getBalance();
		BigDecimal bAdded = new BigDecimal(100.00);
		try
		{
			card.addBalance(user.getDni(), user.getNcard(), bAdded);
		}
		catch(Exception e) 
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals("IncorrectAddBalance",card.getBalance(),bal.add(bal.add(bAdded)));
	}
	
	@Test
	public void deleteBalanceCheckCard()
	{
		BigDecimal bal = card.getBalance();
		BigDecimal bPay = new BigDecimal(100.00);
		try
		{
			card.deleteBalance(user.getDni(), user.getNcard(), bPay);
		}
		catch(Exception e) 
		{
			System.err.println(e.getMessage());
		}
		Assert.assertEquals("IncorrectDeleteBalance",card.getBalance(),bal.add(bal.add(bPay.negate())));
	}
}
