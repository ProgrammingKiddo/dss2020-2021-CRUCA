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
public class ProductScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	ProductScreen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
	}
	
	public void productScreen(Cafeteria coffee, Order ord, String type)
	{
		String option;
		boolean correctChoice;
		int counter;
		int chosenProduct = 1;
		int productQuantity = 0;
	    List<Product> availableProducts;
	    
	    do
	    {
	    	availableProducts = coffee.getAvailableProducts();
	    	counter = 1;
	    	correctChoice = false;
	    	
	        System.out.println("Productos disponibles de la categoría \"" + type + "\"");
	        System.out.println("---------------------------------------------");
	        for(Product p: availableProducts)
	        {
	        	if (p.getType().equals(type))
	        	{
	        		System.out.println(counter + ". " + p.getName() + " (" + p.getPrice() + " euros)");
	        		counter++;	        		
	        	}
	        }
	        if (counter == 1)
	        {
	        	System.out.println("- No hay ningun producto disponible de ese tipo.");
	        }
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opcion");
	        option = keyboard.nextLine();
	        
	        if (!option.equalsIgnoreCase("R"))
	        {
	        	// Get the product and the quantity to add
	        	try {
	        		chosenProduct = Integer.parseInt(option);
	        		if (chosenProduct < 1 || chosenProduct > availableProducts.size())
	        		{
	        			throw new NumberFormatException();
	        		}
	        		
	        		productQuantity = keyboard.nextInt();
	        		if (productQuantity < 1)
	        		{
	        			throw new InputMismatchException();
	        		}
	        		
	        		correctChoice = true;
	        	} catch (NumberFormatException ex)
	        	{
	        		System.out.println("\tIntroduzca una opcion valida.");
	        	}
	        	catch (InputMismatchException ex)
	        	{
	        		System.out.println("\tIntroduzca una cantidad de producto valida.");
	        	}
	        	// Add the amount of product to the order
	        	if (correctChoice == true)
	        	{
	        		try {
	        			ordSer.addProductToOrder(coffee, ord, availableProducts.get(chosenProduct-1).getId(), productQuantity);
	        		} catch (InsufficientStockException ex)
	        		{
	        			System.out.println("\tLo sentimos, pero solo tenemos " + coffee.getProductQuantity(availableProducts.get(chosenProduct-1)) + " unidades en stock.");
	        		}
	        	}
	        }

	    } while (!option.equalsIgnoreCase("R"));
	}
}