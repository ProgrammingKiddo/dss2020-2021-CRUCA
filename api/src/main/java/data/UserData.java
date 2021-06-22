package data;

/**
 * Interface that offers operations to save the data of a user.
 * @author Borja
 * @author Maria
 *
 */

import coreapi.User;

public interface UserData 
{
	/**
	 * Return a specific user.
	 * @param dni	The DNI of a specific user.
	 * @return	Return the specific user.
	 */
	public User getUser(int dni);
	
	/**
	 * Save a specific user.
	 * @param usr	The specific user.
	 */
	public void saveUser(User usr);
}
