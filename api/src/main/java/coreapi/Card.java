package coreapi;

/**
 * 
 * @author Fran
 * @author María
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
		this.balance = 0;
	}
	
	public int getUserDni() { return userDni; }
	
	public int getCardNumber() { return cardNumber; }
	
	public BigDecimal getBalance() { return balance; }
	
	public void addBalance(int uDni, int cNumber, BigDecimal bAdded)
	{
		if(uDni == userDni && cNumber == cardNumber)
		{
			if(bAdded > 0)
			{
				balance += bAdded;
			}
			else
			{
				throw WrongTransactionException("Invalid Quantity");
			}
		}
		else {
			throw WrongTransactionException("Wrong Personal Data");
		}
	}
	
	public void deleteBalance(int uDni, int cNumber, BigDecimal bPay)
	{
		if(uDni == userDni && cNumber == cardNumber)
		{
			if(bPay > 0)
			{
				balance -= bPay;
			}
			else
			{
				throw WrongTransactionException("Invalid Quantity");
			}
		}
		else {
			throw WrongTransactionException("Wrong Personal Data");
		}
	}
}
