package com.example.DataBaseCruca;

/**
 * 
 * @author María
 * @author Fran
 */
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Interger id;
	
	private String concept;
	private User user;
	private LocalDateTime date;
	private BigDecimal price;
	
	public Payment(String ccpt, User u, BigDecimal p)
	{
		this.concept = ccpt;
		this.user = u;
		this.price = p;
		this.date = LocalDateTime.now(); 
	}
}