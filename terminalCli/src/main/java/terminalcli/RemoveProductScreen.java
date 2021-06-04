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
public class removeProductScreen
{
	private Scanner keyboard;
	private OrderService ordSer;
	
	removeProductScreen()
	{
		keyboard = new Scanner(System.in);
		ordSer = new OrderService();
	}
	
	public void removeProductScreen(Cafeteria coffee, Order ord)
	{
	    String option;
	    String productString = "";
	    boolean correctChoice;
	    int chosenProduct =1, productQuantity =0;
	    int counter;
	    Map<Product, Integer> basket = ord.getBasket();
	    int productIds[] = new int[basket.size()];
	    do
	    {
	    	counter = 1;
	    	correctChoice = false;
	        System.out.println("Eliminar producto del pedido");
	        System.out.println("---------------------------------------------");
	        for(Map.Entry<Product, Integer> entry : basket.entrySet())
	        {
	        	productString = counter + ". " + entry.getKey().getName() + " (" + entry.getValue().intValue() + " ud";
	            if (entry.getValue().intValue() > 1)
	            {
	            	productString += "s";
	            }
	            productString += " x " + entry.getKey().getPrice().doubleValue() + " euro";
	            if (entry.getKey().getPrice().doubleValue() != 1.0)
	            {
	            	productString += "s";
	            }
	            productString += ")";
	            // We store the corresponding product Id to the given numerical choice position in the array
	            productIds[counter-1] = entry.getKey().getId();
	            counter++;
	        }
	        System.out.println(productString);
	        System.out.println("R. Volver a pantalla anterior");
	        System.out.println("---------------------------------------------");
	        System.out.println("Introduzca una opcion: ");
	        option = keyboard.nextLine();
	        
	        if (!option.equalsIgnoreCase("R"))
	        {
	        	// Get the product and the quantity to remove
	        	try {
	        		chosenProduct = Integer.parseInt(option);
	        		if (chosenProduct < 1 || chosenProduct > basket.size())
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
	        		System.out.println("\tIntroduzca una cantidad de producto a retirar valida.");
	        	}
	        	// Remove the amount of product to the order
	        	if (correctChoice == true)
	        	{
	        		int productId = productIds[chosenProduct-1];
	        		try {
	        			ordSer.removeProductFromOrder(ord, productId, productQuantity);
	        		} catch (InsufficientStockException ex)
	        		{
	        			System.out.println("\tSolo hay " + ord.checkProductQuantity(productId) + " unidade(s) de ese producto en el pedido .");
	        		}
	        		catch (ProductNotContainedInOrderException ex)
	        		{
	        			System.err.println(ex.getMessage());
	        		}
	        	}
	        }

	    } while (!option.equalsIgnoreCase("R"));
	}
}