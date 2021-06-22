package com.example.accessingdatamysql;

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

	public Payment(String ccpt, User u, BigDecimal p)
	{
		this.concept = ccpt;
		this.user = u;
	    this.price = p;
	    this.date = LocalDateTime.now(); 
	}

	public String getConcpt() 
	{ 
		return concept; 
	}

	public User getUser() 
	{ 
		return user; 
	}

	BigDecimal getBalance() 
	{ 
		return price; 
	}
}

