package filepersistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author Borja
 *
 */

import coreapi.User;
import data.UserData;

/**
 * This class represent the functions for load or save the User objects using files
 * @author Maria
 * @author Fran
 * @author Borja
 *
 */
public class DiskUserData implements UserData
{
	private String path;
	
	public DiskUserData(String path)
	{
		this.path = path;
	}
	
	/**
	 * Obtain a specific user by his id
	 * 
	 * @param dni	Identifier of an user
	 * @see User
	 * @return 		Return the specific User object searched
	 */
	public User getUser(int dni)
	{
		User u = null;
		
        try 
        {
            FileInputStream currentUser = new FileInputStream(path + "user" + dni + ".txt");
            ObjectInputStream UserRead = new ObjectInputStream(currentUser);
            u = (User)UserRead.readObject();
            UserRead.close();
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        
        return u;
	}
	
	/**
	 * Save in a file an User object
	 * 
	 * @param usr	User object will be saved
	 * @see user
	 */
	public void saveUser(User usr)
	{
		try {
			FileOutputStream currentUser = new FileOutputStream(path + "user" + usr.getDni() + ".txt");
			ObjectOutputStream UserWrite = new ObjectOutputStream(currentUser);
			UserWrite.writeObject(usr);
			UserWrite.close();
		} catch (Exception ex)
		{
			System.err.println(ex.getMessage());
		}
	}
}
