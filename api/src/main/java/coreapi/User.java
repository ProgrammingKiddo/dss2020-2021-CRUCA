package coreapi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the <code>Product</code> interface representing a menu comprised of multiple products.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Fran
 * @author Borja
 * @author Maria
 * @version 0.2
 */

public class User implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	private int dni;
	private LocalDate BirthDate;
	private int n_card;
	private String name;
	private String surname;
	private String email;
	
	//This list stores all the ids of the orders that the user has made
	private List<Integer> UserOrderList;
	
	public User() 
	{
		UserOrderList = new ArrayList<Integer>();
	}
	/**
	 * Creates a new instance of a specific user.
	 * @param name			The name associated with this user.
	 * @param surname		The surname associated with this user.
	 * @param n_card		The university card number associated with this user.
	 * @param birth_date	The birth date associated with this user.	
	 * @param dni			The DNI associated with this user, this is unique.
	 * @param email			The email associated with this user.
	 */
	public User(String Name, String Surname, int ncard, LocalDate birth_date, int Dni, String Email)
	{
		this.BirthDate = birth_date;
		this.n_card = ncard;
		this.name = Name;
		this.surname = Surname;
		this.dni = Dni;
		this.email = Email;
		UserOrderList = new ArrayList<Integer>();
	}

	/**
	 * Returns the DNI associated with a specific user.
	 * @return	Returns the DNI associated with this user.
	 */
	public int getDni() 
	{
		return dni; 
	}
	
	/**
	 * Returns the university card number associated with a specific user.
	 * @return	Return the university card number associated with this user.
	 */
	public int getNcard()
	{ 
		return n_card; 
	}
	
	/**
	 * Returns the name associated with a specific user.
	 * @return	Return the name associated with this user.
	 */
	public String getName()
	{ 
		return name; 
	}
	
	/**
	 * Returns the surname associated with a specific user.
	 * @return	Return the surname associated with this user. 
	 */
	public String getSurname()
	{ 
		return surname; 
	}
	
	/**
	  Returns the email associated with a specific user.
	 * @return	Return the email associated with this user.
	 */
	public String getEmail() 
	{ 
		return email; 
	}
	
	/**
	* Returns the list of orders made by the user.
	* @return Returns the list of orders made by the user.
	*/
	public List<Integer> getUserOrderList()
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
	
	/**
	 * Set the name of this user to the one passed by parameter.
	 * @param Name	The new name to set this user.
	 */
	public void setName(String Name) 
	{
		this.name = Name;
	}
	
	/**
	 * Set the surname of this user to the one passed by parameter.
	 * @param Surname	The new surname to set this user.
	 */
	public void setSurname(String Surname) 
	{
		this.surname = Surname;
	}
	
	/**
	 * Set the university card number of this user to the one passed by parameter.
	 * @param N_card	The new university card number to set this user.
	 */
	public void setNcard(int N_card) 
	{
		this.n_card = N_card;
	}
	
	/**
	 * Set the birth date of this user to the one passed by parameter.
	 * @param newDate	The new birth date to set this user.
	 */
	public void setBirthDate(LocalDate newDate) 
	{
		this.BirthDate = newDate;
	}
	
	/**
	 * Set the email of this user to the one passed by parameter.
	 * @param newEmail	The new email to set this user.
	 */
	public void setEmail(String newEmail) 
	{ 
		this.email = newEmail;
	}
	
	/**
	 * Set the DNI of this user to the one passed by parameter.
	 * @param dni	The new DNI to set this user.
	 */
	public void setDNI(int dni)
	{
		this.dni = dni;
	}
	
	/**
	 * Returns the BirthDate of the user.
	 * @return	The BirthDate of the user.
	 */
	public LocalDate getBirthDate()
	{
		return BirthDate;
	}
	
	/**
	 * Set the new order id to the list of the user order.
	 * @param ordID		The new order to set to the list.
	 */
	public void setOrder(int ordID)
	{
		UserOrderList.add(Integer.valueOf(ordID));
	}
	
	/**
	 * Delete a specific order of the list of the user order.
	 * @param ordID		The order we want to remove.
	 */
	public void deleteOrder(int ordID)
	{
		UserOrderList.remove(Integer.valueOf(ordID));
	}
}
