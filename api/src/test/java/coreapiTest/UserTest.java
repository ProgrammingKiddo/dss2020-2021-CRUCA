/**
 * @author María
 */
package coreapiTest;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Assert;
import org.junit.Test;

import coreapi.*;

public class UserTest 
{
	private User user = new User("Alberto","Lozano",123456789,LocalDate.parse("1996-05-20"), 45678904,"albertolozano@gmail.com");
	
	@Test
	public void DniCheckUser()
	{
		Assert.assertEquals("DifferentDniAtUser", 45678904, user.getDni());
	}
	
	@Test
	public void NcardCheckUser()
	{
		Assert.assertEquals("DifferentNcardAtUser", 123456789, user.getNcard());
	}
	
	@Test
	public void NameCheckUser()
	{
		Assert.assertEquals("DifferentNameAtUser", "Alberto", user.getName());
	}
	
	@Test
	public void SurnameCheckUser()
	{
		Assert.assertEquals("DifferentSurnameAtUser", "Lozano", user.getSurname());
	}
	
	@Test
	public void EmailCheckUser()
	{
		Assert.assertEquals("DifferentEmailAtUser", "albertolozano@gmail.com", user.getEmail());
	}
	
	@Test
	public void AgeCheckUser()
	{
		Period p = Period.between(LocalDate.now(), LocalDate.parse("1996-05-20"));
		Assert.assertEquals("DifferentAgeAtUser", p.getYears() , user.getAge());
	}
	
	
}
