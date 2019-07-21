package services;
import java.util.List;

import beans.Employee;

public class LoginService 
{
	public static boolean userAuthenticate(List<Employee> emps, String username, String password) 
	{
		for(Employee em: emps) 
		{
			if(em.getUsername() == username && em.getPassword() == password) 
			{
				return true;
			}
		}
		return false;
	}
}
