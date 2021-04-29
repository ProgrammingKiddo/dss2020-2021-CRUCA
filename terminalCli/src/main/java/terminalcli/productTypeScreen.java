package terminalcli;

import coreapi.Product;
import coreapi.ProductNotContainedInOrderException;
import coreapi.UnreachableStatusException;
import coreapi.Cafeteria;
import coreapi.InsufficientStockException;
import coreapi.Order;
import coreapi.OrderService;
import coreapi.OrderFactory;

import java.util.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;


/**
 * Implements all the functionality and interface for the user.
 * @author Maria
 * @author Fran
 * @author Borja
 * @version 0.1
 */
public class productTypeScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	productTypeScreen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
	}
	
	public void productTypeScreen(Cafeteria coffee, Order ord)
	{
		String option;
	    int counter;
	    int chosenType;
	    List<String> types;
	    
	    do
	    {
	    	// The list of types should be obtained right before each possible operation,
	    	// because it can change afterwards.
	    	types = coffee.getTypes();
	    	counter = 1;
	        System.out.println("Tipos de productos");
	        System.out.println("---------------------------------------------");
	        for (String type: types)
	        {
	            System.out.println(counter + ". " + type);
	            counter++;
	        }
	        System.out.println(".....");
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.print("Introduzca una opcion: ");
	        option = keyboard.nextLine();

	        if (!option.equalsIgnoreCase("R"))
	        {
	        	try
	        	{
	        		chosenType = Integer.parseInt(option);
	        		if (chosenType < 1 || chosenType > types.size())
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		productScreen(coffee, ord, types.get(chosenType-1));
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        }
	    } while (!option.equalsIgnoreCase("R"));
	}
}