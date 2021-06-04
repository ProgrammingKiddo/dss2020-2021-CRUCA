package data;

/**
 * 
 * @author Borja
 *
 */

import coreapi.User;

public interface UserData {

	public User getUser(int dni);
	public void saveUser(User usr);
}
