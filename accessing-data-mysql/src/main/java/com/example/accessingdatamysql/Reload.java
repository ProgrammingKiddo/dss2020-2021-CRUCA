package com.example.accessingdatamysql;

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

	public int getDNI() 
	{ 
		return dni; 
	}

	public int getNCard() 
	{ 
		return nCard; 
	}

	public BigDecimal getQuantity() 
	{ 
		return quantity; 
	}

}
