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

	public Reload() {}
	public Reload(int DNI, int nc, BigDecimal q)
	{
		this.dni = DNI;
	    this.nCard = nc;
	    this.quantity = q;
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
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}
	/**
	 * @param nCard the nCard to set
	 */
	public void setnCard(int nCard) {
		this.nCard = nCard;
	}
	/**
	 * @param quantity the quantity to set
	 */
	
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

}
