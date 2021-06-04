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

public class DiskUserData implements UserData
{
	private String path;
	
	public DiskUserData(String path)
	{
		this.path = path;
	}
	
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
