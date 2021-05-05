package coreapi;

import java.io.Serializable;
import java.lang.Interger;
import java.math.BigDecimal;
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
public class User, Serializable {
	
	private static final long serialVersionUID = 1L;
	private LocalDate BirthDate;
	private int n_card;
	private string name;
	private string surname;
	//This list stores all the orders that the user has made
	private List<Order> UserOrderList;
	
	public User(string name, string surname, String n_card, LocalDate birth_date)
	{
		this.BirthDate = birth_date;
		this.n_card = Interger.parseUnsignedInt(n_card);
		this.name = name;
		this.surname = surname;
	}
	
	public int get_n_card(){ return n_card; }
	
	public string get_name(){ return name; }
	
	/**
	* Calculates and returns the user's age.
	* @return Returns user's age.
	*/
	public Period get_age(){
		
		LocalDate today = LocalDate.now();
		Period age = today.until(BrithDate);
		
		return age.getYears(); 
	} 
	
	public string get_surname(){ return surname; }
	
	/**
	* Returns the list of orders made by the user.
	* @return Returns the list of orders made by the user.
	*/
	public List<Order> get_UserOrderList()
	{
		return List.copyOf(UserOrderList);
	}
	
	
	public void set_name(String Name) {this.name = Name;}
	
	public void set_surname(String Surname) {this.surname = Surname;}
	
	public void set_n_card(String N_card) {this.n_card = Interger.parseUnsignedInt(N_card);}
	
	public void set_BirthDate(LocalDate newDate) {this.BirthDate = newDate;}
	

}
