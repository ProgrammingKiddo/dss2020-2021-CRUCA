package coreapi;

import java.math.BigDecimal;

/**
 * 
 * @author Fran
 * @author Mar�a
 * @author Borja
 *
 */

public class Card {

	private int userDni;
	private int cardNumber;
	private BigDecimal balance;
	
	public Card(int dni, int cNumber)
	{
		this.userDni = dni;
		this.cardNumber = cNumber;
	}
	
	public int getUserDni() { return userDni; }
	
	public int getCardNumber() { return cardNumber; }
	
	public BigDecimal getBalance() { return balance; }
	
	public void addBalance(int uDni, int cNumber, BigDecimal bAdded) throws WrongTransactionException
	{
		if(uDni == userDni && cNumber == cardNumber)
		{
			// bAdded > 0
			if(bAdded.compareTo(BigDecimal.ZERO) == 1)
			{
				balance = balance.add(bAdded);
			}
			else
			{
				throw new WrongTransactionException("Invalid Quantity");
			}
		}
		else {
			throw new WrongTransactionException("Wrong Personal Data");
		}
	}
	
	public void deleteBalance(int uDni, int cNumber, BigDecimal bPay) throws WrongTransactionException
	{
		if(uDni == userDni && cNumber == cardNumber)
		{
			//bPay > 0
			if(bPay.compareTo(BigDecimal.ZERO) == 1)
			{
				balance = balance.add(bPay.negate());
			}
			else
			{
				throw new WrongTransactionException("Invalid Quantity");
			}
		}
		else {
			throw new WrongTransactionException("Wrong Personal Data");
		}
	}
}
