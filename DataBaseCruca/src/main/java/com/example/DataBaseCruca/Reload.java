package com.example.DataBaseCruca;
/**
 * 
 * @author María
 * @author Fran
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jdk.internal.org.jline.reader.impl.history.DefaultHistory;

@Entity
public class Reload {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Interger id;
	
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
}
