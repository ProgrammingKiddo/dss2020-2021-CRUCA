package coreapi;

import java.io.Serializable;
import java.lang.Integer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.time.*;

/**
 * Implementation of the <code>Product</code> interface representing a menu comprised of multiple products.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Fran
 * @author Borja
 * @author Maria
 * @version 0.2
 */
@Component
public class User implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	private int dni;
	private LocalDate BirthDate;
	private int n_card;
	private String name;
	private String surname;
	
	//This list stores all the orders that the user has made
	private List<Order> UserOrderList;
	
	public User(String name, String surname, String n_card, LocalDate birth_date, int dni)
	{
		this.BirthDate = birth_date;
		this.n_card = Integer.parseUnsignedInt(n_card);
		this.name = name;
		this.surname = surname;
		this.dni = dni;
	}

	public int getDni() {return dni; }
	
	public int getNcard(){ return n_card; }
	
	public String getName(){ return name; }
	
	public String getSurname(){ return surname; }
	
	/**
	* Returns the list of orders made by the user.
	* @return Returns the list of orders made by the user.
	*/
	public List<Order> getUserOrderList()
	{
		return List.copyOf(UserOrderList);
	}
	
	/**
	* Calculates and returns the user's age.
	* @return Returns user's age.
	*/
	public int getAge()
	{
		LocalDate today = LocalDate.now();
		int age = today.getYear() - BirthDate.getYear();

		// Not elegant but works
		if (today.getMonth().getValue() < BirthDate.getMonth().getValue())
		{
			age--;
		}
		else
		{
			if (today.getMonthValue() == BirthDate.getMonthValue() && today.getDayOfMonth() < BirthDate.getDayOfMonth())
			{
				age--;
			}
		}
		return age; 
	} 
	
	public void setName(String Name) {this.name = Name;}
	
	public void setSurname(String Surname) {this.surname = Surname;}
	
	public void setNcard(String N_card) {this.n_card = Integer.parseUnsignedInt(N_card);}
	
	public void setBirthDate(LocalDate newDate) {this.BirthDate = newDate;}
	

}
