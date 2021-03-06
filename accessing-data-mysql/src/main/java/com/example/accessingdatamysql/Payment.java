package com.example.accessingdatamysql;
/**
 * Contains all the information and operations related to a payment.
 * @author Fran
 * @author Maria
 */
import java.time.LocalDateTime;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import coreapi.*;

@Entity
public class Payment 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String concept;
	private User user;
	private LocalDateTime date;
	private BigDecimal price;

	public Payment()
	{
		this.date = LocalDateTime.now(); 
	}
	public Payment(String ccpt, User u, BigDecimal p)
	{
		this.concept = ccpt;
		this.user = u;
	    this.price = p;
	    this.date = LocalDateTime.now(); 
	}

	/**
	 * Returns the payment concept.
	 * @return	Return the concept.
	 */
	public String getConcpt() 
	{ 
		return concept; 
	}

	/**
	 * Returns the user who has made the payment.
	 * @return	Returns the user.
	 */
	public User getUser() 
	{ 
		return user; 
	}

	/**
	 * Returns the payment amount.
	 * @return	Return the amount.
	 */
	BigDecimal getBalance() 
	{ 
		return price; 
	}
}

