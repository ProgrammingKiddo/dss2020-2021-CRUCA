package coreapi;

import java.math.BigDecimal;

/**
 * Class that represents the users' university card with its associated card number,
 * DNI of the user to which it belongs and balance.
 * @author Fran
 * @author Mar�a
 * @author Borja
 */

public class Card {

	private int userDni;
	private int cardNumber;
	private BigDecimal balance;
	
	/**
	 * Create new Card instances, with the associated card number 
	 * and DNI of the corresponding user.
	 * @param dni		DNI of the user to whom the university card belongs.
	 * @param cNumber	associated card number, this is unique.
	 */
	public Card(int dni, int cNumber)
	{
		this.userDni = dni;
		this.cardNumber = cNumber;
		balance = BigDecimal.ZERO;
	}
	
	/**
	 * Returns the DNI associated with the user to which a certain card belongs.
	 * @return	the  DNI associated with the user of this card.
	 */
	public int getUserDni() 
	{ 
		return userDni; 
	}
	
	/**
	 * Returns the card number associated with a specific card.
	 * @return	the card number associated with this card.
	 */
	public int getCardNumber() 
	{ 
		return cardNumber; 
	}
	
	/**
	 * Returns the card balance associated with a specific card.
	 * @return the balance associated with this card.
	 */
	public BigDecimal getBalance() 
	{ 
		return balance; 
	}
	
	/**
	 * Add the balance desired by the card user to the card.
	 * @param uDni		DNI of the user to whom the university card belongs.
	 * @param cNumber	associated card number, this is unique.
	 * @param bAdded	amount of balance to add to the card.
	 * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
	 */
	public void addBalance(int cNumber, BigDecimal bAdded) throws WrongTransactionException
	{
		if(bAdded.compareTo(BigDecimal.ZERO) == 1)
		{
			balance = balance.add(bAdded);
		}
		else
		{
			throw new WrongTransactionException("Invalid Quantity");
		}
	}
	
	/**
	 * Eliminate a certain amount of balance to the card of the certain user.
	 * @param uDni		DNI of the user to whom the university card belongs.
	 * @param cNumber	associated card number, this is unique.
	 * @param bPay		amount of balance to withdraw.
	 * @throws WrongTransactionException	If the operation to be performed 
	 * 										fails because the data entered is incorrect.
	 */
	public void deleteBalance(int uDni, int cNumber, BigDecimal bPay) throws WrongTransactionException
	{
		if(uDni == userDni && cNumber == cardNumber)
		{
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
