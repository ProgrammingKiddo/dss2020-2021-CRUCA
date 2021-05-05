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
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private LocalDate BirthDate;
	private int n_card;
	private String name;
	private String surname;
<<<<<<< Updated upstream
	//This list stores all the orders that the user has made
	private List<Order> UserOrderList;
=======
>>>>>>> Stashed changes
	
	public User(String name, String surname, String n_card, LocalDate birth_date)
	{
		this.BirthDate = birth_date;
		this.n_card = Integer.parseUnsignedInt(n_card);
		this.name = name;
		this.surname = surname;
	}
	
	public int get_n_card(){ return n_card; }
	
	public String get_name(){ return name; }
	
<<<<<<< Updated upstream
	/**
	* Calculates and returns the user's age.
	* @return Returns user's age.
	*/
	public Period get_age(){
		
		LocalDate today = LocalDate.now();
		Period age = today.until(BrithDate);
		
		return age.getYears(); 
	} 
	
	public String get_surname(){ return surname; }
	
	/**
	* Returns the list of orders made by the user.
	* @return Returns the list of orders made by the user.
	*/
	public List<Order> get_UserOrderList()
	{
		return List.copyOf(UserOrderList);
	}
	
=======
	
	public int get_age()
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
	} //Cambiar a diferencia entre fechas
	
	public String get_surname(){ return surname; }
>>>>>>> Stashed changes
	
	public void set_name(String Name) {this.name = Name;}
	
	public void set_surname(String Surname) {this.surname = Surname;}
	
	public void set_n_card(String N_card) {this.n_card = Integer.parseUnsignedInt(N_card);}
	
	public void set_BirthDate(LocalDate newDate) {this.BirthDate = newDate;}
	

}
