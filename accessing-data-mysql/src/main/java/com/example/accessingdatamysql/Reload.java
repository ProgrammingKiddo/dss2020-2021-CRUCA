package com.example.accessingdatamysql;

/**
 * Contains all the information and operations related to a reload.
 * @author Fran
 * @author Maria
 */
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reload 
{	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private int dni;
	private int nCard;
	private BigDecimal quantity;
	private BigDecimal afterBalance;

	public Reload(int DNI, int nc, BigDecimal q, BigDecimal ab)
	{
		this.dni = DNI;
	    this.nCard = nc;
	    this.quantity = q;
	    this.afterBalance = ab;
	}

	/**
	 * Returns the DNI of the user being reloaded.
	 * @return	Returns the DNI of the user.
	 */
	public int getDNI() 
	{ 
		return dni; 
	}

	/**
	 * Returns the card number on which the reload is made.
	 * @return Return the card number.
	 */
	public int getNCard() 
	{ 
		return nCard; 
	}

	/**
	 * Returns the amount to reload.
	 * @return	Return the amount.
	 */
	public BigDecimal getQuantity() 
	{ 
		return quantity; 
	}

}
